package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.CallSuper
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

abstract class HiltBroadcastReceiver : BroadcastReceiver() {
    @CallSuper
    override fun onReceive(context: Context?, intent: Intent?) {
    }
}

@AndroidEntryPoint
class NotificationReceiver : BroadcastReceiver() {
    @Inject
    lateinit var activityRepository: ActivityRepositoryInterface

    override fun onReceive(context: Context?, intent: Intent?) {
//        super.onReceive(context, intent)
        if (intent != null && context != null) {
            var allActivity = arrayOf("")
            GlobalScope.launch(Dispatchers.IO) {
                activityRepository.getTodayActivity().value?.forEach(Consumer { item: Activity ->
                    if (item != null) {
                        val date = Date(item.date)
                        allActivity[0] += item.title + " - " +
                                SimpleDateFormat.getTimeInstance().format(date) + "\n"
                    }
                })
                withContext(Dispatchers.Main) {
                    NotificationCreator(context, allActivity[0]).createNotification()
                }
            }

        }
    }
}