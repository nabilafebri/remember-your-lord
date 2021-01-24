package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.MainActivity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State;

@AndroidEntryPoint
public class AddActFragment extends Fragment
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    private Calendar calendar = Calendar.getInstance();
    private static final String calendarName = "Activity Calendar";
    private AddActViewModel addActViewModel;
    private EditText editTextTitle;
    private EditText editTextDesc;
    private TextView textViewDate;
    private Button btnSubmit;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_act, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addActViewModel = ViewModelProviders.of(this).get(AddActViewModel.class);

        editTextTitle = view.findViewById(R.id.add_act_title);
        editTextDesc = view.findViewById(R.id.add_act_desc);
        textViewDate = view.findViewById(R.id.in_add_act_date);
        btnSubmit = view.findViewById(R.id.btn_add_act);
        progressBar = view.findViewById(R.id.add_act_loading);

        addActViewModel.getIsDatePicked().observe(getViewLifecycleOwner(), activity -> {
            btnSubmit.setVisibility(View.VISIBLE);
            if (activity) {
                textViewDate.setText(SimpleDateFormat.getDateTimeInstance().format(
                        new Date(calendar.getTimeInMillis())
                ));
            }
        });

        addActViewModel.getActivity().observe(getViewLifecycleOwner(), activity -> {
            if (activity instanceof State.Success) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),
                        getString(R.string.add_act_success),
                        Toast.LENGTH_LONG).show();
            } else if (activity instanceof State.Loading) {
                showLoading();
            } else if (activity instanceof State.Failed) {
                hideLoading();
            } else if (activity instanceof State.Initialize) {
                hideLoading();
            };
        });

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(view);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCalendarPermissonAndAddActToCal(requireView().getContext());
            }
        });
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showDatePicker(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),this,
                year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this.getContext(), this, 0, 0,
                DateFormat.is24HourFormat(this.getContext()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, hourOfDay);
        addActViewModel.setIsDatePicked();
    }

    public void submitAct(Context context) {
        String title = editTextTitle.getText().toString().trim();
        String desc = editTextDesc.getText().toString().trim();
        long date = calendar.getTimeInMillis();
        addActViewModel.addActivity(title, desc, date);
        addActToCal(context, title, desc, date);
    }

    @SuppressLint("MissingPermission")
    public long addActToCal(
            Context context,
            String title,
            String desc,
            long date
    ) {

        long calId = getCalendarId(context);
        if (calId == -1L) {
            calId = makeCalendar(context);
        }

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, date);
        values.put(CalendarContract.Events.DTEND, date);
        values.put(CalendarContract.Events.TITLE, title);
        if (desc != null) {
            values.put(CalendarContract.Events.DESCRIPTION, desc);
        }
        values.put(CalendarContract.Events.CALENDAR_ID, calId);
        values.put(CalendarContract.Events.ORGANIZER, "nabilafv24@gmail.com");
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Jakarta");

        Uri uri = context.getContentResolver().insert(
                CalendarContract.Events.CONTENT_URI,
                values);
        addActViewModel.finishAddingToCalendar();
        return  Long.parseLong(uri.getLastPathSegment());
    }

    @SuppressLint("MissingPermission")
    private long getCalendarId(Context context) {
        String[] projection = new String[]{CalendarContract.Calendars._ID};
        String selection = CalendarContract.Calendars.ACCOUNT_NAME + " = ? AND " +
                CalendarContract.Calendars.ACCOUNT_TYPE + " = ? ";
        String[] selArgs = {calendarName, CalendarContract.ACCOUNT_TYPE_LOCAL};
        Cursor cursor = context.getContentResolver().query(
                CalendarContract.Calendars.CONTENT_URI,
                projection,
                selection,
                selArgs,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return  cursor.getLong(0);
            }
        }
        return -1;
    }

    private long makeCalendar(Context context) {
        ContentValues values = new ContentValues();
        values.put(
                CalendarContract.Calendars.ACCOUNT_NAME,
                calendarName
        );
        values.put(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL
        );
        values.put(
                CalendarContract.Calendars.NAME,
                "RYL - Activity Calendar"
        );
        values.put(
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                "RYL - Activity Calendar"
        );
        values.put(
                CalendarContract.Calendars.CALENDAR_COLOR,
                -0x10000
        );
        values.put(
                CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
                CalendarContract.Calendars.CAL_ACCESS_OWNER
        );
        values.put(
                CalendarContract.Calendars.OWNER_ACCOUNT,
                "nabilafv24@gmail.com"
        );
        values.put(
                CalendarContract.Calendars.CALENDAR_TIME_ZONE,
                "Asia/Jakarta"
        );
        values.put(
                CalendarContract.Calendars.SYNC_EVENTS,
                1
        );

        Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_NAME,
                calendarName
        );
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL
        );
        builder.appendQueryParameter(
                CalendarContract.CALLER_IS_SYNCADAPTER,
                "true"
        );

        Uri uri = context.getContentResolver().insert(builder.build(), values);
        return Long.parseLong(uri.getLastPathSegment());
    }

    private ActivityResultLauncher<String[]> requestPermissions =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                if (!isGranted.containsValue(false)) {
                    submitAct(requireView().getContext());
                }
            });

    private void checkCalendarPermissonAndAddActToCal(Context context) {
        if ((PermissionChecker.checkSelfPermission(
                context, Manifest.permission.READ_CALENDAR)
                == PermissionChecker.PERMISSION_GRANTED) &&
                (PermissionChecker.checkSelfPermission(
                        context, Manifest.permission.WRITE_CALENDAR)
                        == PermissionChecker.PERMISSION_GRANTED)) {
            submitAct(context);
        } else if (shouldShowRequestPermissionRationale("Want to add activity to your calender")) {
        } else {
            String[] calendarPermissions = {Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR};
            requestPermissions.launch(calendarPermissions);
        }
    }
}
