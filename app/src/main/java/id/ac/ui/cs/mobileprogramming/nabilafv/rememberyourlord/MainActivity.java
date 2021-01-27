package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.receiver.NotificationReceiver;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.ActDetailFragment;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.ActListViewModel;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.AddActActivity;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActListViewModel actListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btnAddAct = findViewById(R.id.btn_add_activity);
        btnAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActActivity.class));
            }
        });

        actListViewModel = new ViewModelProvider(this).get(ActListViewModel.class);
        actListViewModel.getSelectedActivity().observe(this, item -> {
            if (findViewById(R.id.fragment_activity_detail) == null) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main_activity, new ActDetailFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        setupDailyNotif();
    }

    // at 9 am
    private void setupDailyNotif() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT
        );

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
    }
}