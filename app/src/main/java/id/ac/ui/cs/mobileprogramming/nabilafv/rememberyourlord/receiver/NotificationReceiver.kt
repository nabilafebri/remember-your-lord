package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepositoryInterface
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.NotificationCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class NotificationReceiver : BroadcastReceiver() {
    @Inject
    lateinit var activityRepository: ActivityRepositoryInterface

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && context != null) {
            var allActivity = ""
            GlobalScope.launch(Dispatchers.IO) {
                activityRepository.getTodayActivity().forEach { item ->
                    if (item != null) {
                        val date = Date(item.date)
                        allActivity += "${item.title} - ${
                            SimpleDateFormat.getTimeInstance().format(date)}\n"
                    }
                }
                withContext(Dispatchers.Main) {
                    NotificationCreator(context, allActivity).createNotification()
                }
            }

        }
    }
}