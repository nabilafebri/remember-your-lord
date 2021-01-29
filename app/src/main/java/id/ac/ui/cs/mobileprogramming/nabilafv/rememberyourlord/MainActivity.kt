package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.ActivityMainBinding
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.receiver.NotificationReceiver
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.ActDetailFragment
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.ActListViewModel
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.AddActActivity
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var actListViewModel: ActListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActActivity::class.java))
        }
        actListViewModel = ViewModelProvider(this).get(ActListViewModel::class.java)
        actListViewModel.getSelectedActivity().observe(this) { item ->
            if (findViewById<View?>(R.id.fragment_activity_detail) == null) {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.layout_main_activity, ActDetailFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
        setupDailyNotif()
    }

    // at 9 am
    private fun setupDailyNotif() {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 10
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        val intent = Intent(applicationContext, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val alarmManager = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}