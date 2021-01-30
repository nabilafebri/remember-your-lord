package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.content.PermissionChecker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.FragmentAddActBinding
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddActFragment : Fragment(), OnDateSetListener, OnTimeSetListener {
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var addActViewModel: AddActViewModel
    private var _binding: FragmentAddActBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddActBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addActViewModel = ViewModelProvider(requireActivity()).get(AddActViewModel::class.java)

        addActViewModel.getIsDatePicked().observe(viewLifecycleOwner, { activity ->
            binding.btnAddAct.isVisible = activity
            if (activity) {
                binding.inAddActDate.text = SimpleDateFormat.getDateTimeInstance().format(
                    Date(calendar.timeInMillis)
                )
            }
        })

        addActViewModel.getActivity().observe(viewLifecycleOwner, { activity ->
            if (activity is State.Success) {
                binding.addActLoading.visibility = View.GONE
                Toast.makeText(context, getString(R.string.add_act_success), Toast.LENGTH_LONG)
                    .show()
            } else if (activity is State.Loading) {
                showLoading()
            } else if (activity is State.Failed) {
                hideLoading()
            } else if (activity is State.Initialize) {
                hideLoading()
            }
        })

        binding.inAddActDate.setOnClickListener { showDatePicker(view) }

        binding.btnAddAct.setOnClickListener {
            checkCalendarPermissonAndAddActToCal(requireView().context)
        }
    }

    fun hideLoading() {
        binding.addActLoading.visibility = View.GONE
    }

    fun showLoading() {
        binding.addActLoading.visibility = View.VISIBLE
    }

    private fun showDatePicker(view: View) {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val datePickerDialog = DatePickerDialog(view.context, this, year, month, day)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val timePickerDialog = TimePickerDialog(
            this.context, this, 0, 0,
            DateFormat.is24HourFormat(this.context)
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, hourOfDay)
        addActViewModel.setIsDatePicked()
    }

    fun submitAct(context: Context) {
        val title = binding.addActTitle.text?.trim().toString()
        val desc = binding.addActDesc.text?.trim().toString()
        val date = calendar.timeInMillis
        addActViewModel.addActivity(title, desc, date)
        addActToCal(context, title, desc, date)
    }

    @SuppressLint("MissingPermission")
    fun addActToCal(
        context: Context,
        title: String,
        desc: String,
        date: Long
    ): Long? {
        var calId: Long? = getCalendarId(context)
        if (calId == -1L) {
            calId = makeCalendar(context)
        }

        val values = ContentValues()
        values.put(CalendarContract.Events.DTSTART, date)
        values.put(CalendarContract.Events.DTEND, date)
        values.put(CalendarContract.Events.TITLE, title)
        values.put(CalendarContract.Events.DESCRIPTION, desc)
        values.put(CalendarContract.Events.CALENDAR_ID, calId)
        values.put(CalendarContract.Events.ORGANIZER, "nabilafv24@gmail.com")
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Jakarta")

        val uri: Uri? = context.contentResolver.insert(
            CalendarContract.Events.CONTENT_URI,
            values
        )
        addActViewModel.finishAddingToCalendar()
        return uri?.lastPathSegment?.toLong()
    }

    @SuppressLint("MissingPermission")
    private fun getCalendarId(context: Context): Long {
        val projection = arrayOf(CalendarContract.Calendars._ID)
        val selection = CalendarContract.Calendars.ACCOUNT_NAME + " = ? AND " +
                CalendarContract.Calendars.ACCOUNT_TYPE + " = ? "
        val selArgs = arrayOf(calendarName, CalendarContract.ACCOUNT_TYPE_LOCAL)
        val cursor: Cursor? = context.contentResolver.query(
            CalendarContract.Calendars.CONTENT_URI,
            projection,
            selection,
            selArgs,
            null
        )
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor.getLong(0)
            }
        }
        return -1
    }

    private fun makeCalendar(context: Context): Long? {
        val values = ContentValues()
        values.put(
            CalendarContract.Calendars.ACCOUNT_NAME,
            calendarName
        )
        values.put(
            CalendarContract.Calendars.ACCOUNT_TYPE,
            CalendarContract.ACCOUNT_TYPE_LOCAL
        )
        values.put(
            CalendarContract.Calendars.NAME,
            "RYL - Activity Calendar"
        )
        values.put(
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            "RYL - Activity Calendar"
        )
        values.put(
            CalendarContract.Calendars.CALENDAR_COLOR,
            -0x10000
        )
        values.put(
            CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
            CalendarContract.Calendars.CAL_ACCESS_OWNER
        )
        values.put(
            CalendarContract.Calendars.OWNER_ACCOUNT,
            "nabilafv24@gmail.com"
        )
        values.put(
            CalendarContract.Calendars.CALENDAR_TIME_ZONE,
            "Asia/Jakarta"
        )
        values.put(
            CalendarContract.Calendars.SYNC_EVENTS,
            1
        )
        val builder: Uri.Builder = CalendarContract.Calendars.CONTENT_URI.buildUpon()
        builder.appendQueryParameter(
            CalendarContract.Calendars.ACCOUNT_NAME,
            calendarName
        )
        builder.appendQueryParameter(
            CalendarContract.Calendars.ACCOUNT_TYPE,
            CalendarContract.ACCOUNT_TYPE_LOCAL
        )
        builder.appendQueryParameter(
            CalendarContract.CALLER_IS_SYNCADAPTER,
            "true"
        )
        val uri: Uri? = context.contentResolver.insert(builder.build(), values)
        return uri?.lastPathSegment?.toLong()
    }

    private val requestPermissions = registerForActivityResult(RequestMultiplePermissions())
    { isGranted ->
        if (!isGranted.containsValue(false)) {
            submitAct(requireView().context)
        }
    }

    private fun checkCalendarPermissonAndAddActToCal(context: Context) {
        when {
            ((PermissionChecker.checkSelfPermission(context, Manifest.permission.READ_CALENDAR)
                    == PermissionChecker.PERMISSION_GRANTED) &&
                    (PermissionChecker.checkSelfPermission(
                        context, Manifest.permission.WRITE_CALENDAR
                    )
                            == PermissionChecker.PERMISSION_GRANTED)) -> {
                submitAct(context)
            }
            shouldShowRequestPermissionRationale("Want to add activity to your calender")
            -> {
            }
            else -> {
                val calendarPermissions = arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR
                )
                requestPermissions.launch(calendarPermissions)
            }
        }
    }

    companion object {
        private const val calendarName = "Activity Calendar"
    }
}