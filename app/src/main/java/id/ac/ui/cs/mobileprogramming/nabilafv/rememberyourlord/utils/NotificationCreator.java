package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationChannel;
import android.os.Build;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.MainActivity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;

public class NotificationCreator {
    private Context mContext;
    private String activities;

    public NotificationCreator(Context mContext, String activities) {
        this.mContext = mContext;
        this.activities = activities;
    }

    public void createNotification() {
        Intent rUrLIntent = new Intent(mContext, MainActivity.class);
        rUrLIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, 0, rUrLIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        String NOTIFICATION_CHANNEL_ID = "yeyeyelalalala";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                mContext, NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_setting).setContentTitle("Good Morning")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Your today activities are\n" + activities)
                )
                .setAutoCancel(false)
                .setContentIntent(pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "My Notifications",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        mNotificationManager.notify(0, mBuilder.build());
    }
}
