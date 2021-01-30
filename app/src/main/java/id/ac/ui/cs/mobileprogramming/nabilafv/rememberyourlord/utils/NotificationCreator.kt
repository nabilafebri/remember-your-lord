package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils

import android.R
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.MainActivity


internal class NotificationCreator(private val mContext: Context, private val activities: String) {
    fun createNotification() {
        val rUrLIntent = Intent(mContext, MainActivity::class.java)
        rUrLIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(
            mContext, 0, rUrLIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val mBuilder = NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID)
        mBuilder.setSmallIcon(R.drawable.ic_dialog_info).setContentTitle("Good Morning")
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText("Your today activities are\n$activities")
            )
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)
        val mNotificationManager =
            mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "My Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        mNotificationManager.notify(0, mBuilder.build())
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "notifchid"
    }
}