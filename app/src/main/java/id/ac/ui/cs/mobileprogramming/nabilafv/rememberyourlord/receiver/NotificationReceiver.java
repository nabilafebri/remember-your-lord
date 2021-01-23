package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.MainActivity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepositoryInterface;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.NotificationCreator;
import kotlinx.coroutines.GlobalScope;

abstract class HiltBroadcastReceiver extends BroadcastReceiver {
    public HiltBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    }
}

@AndroidEntryPoint
public class NotificationReceiver extends HiltBroadcastReceiver {

    @Inject
    ActivityRepositoryInterface activityRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null && context != null) {
            String[] allActivity = {""};

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    activityRepository.getTodayActivity().forEach((item) -> {
                    if(item != null) {
                        Date date = new Date(item.getDate());
                        allActivity[0] += item.getTitle() + " - " + SimpleDateFormat.getTimeInstance().format(date);
                    }
                    });
                }
            });
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new NotificationCreator(context, allActivity[0]).createNotification();
        }
    }

}
