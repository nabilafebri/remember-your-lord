package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.ActivityDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity
import java.util.*
import javax.inject.Inject


class ActivityRepository @Inject constructor(private val activityDao: ActivityDao) :
    ActivityRepositoryInterface {
    override suspend fun insertActivity(title: String, description: String, date: Long) {
        activityDao.insertActivity(
            Activity(UUID.randomUUID().toString(), title, description, false, date)
        )
    }

    override suspend fun updateActivity(idAct: String) {
        activityDao.updateActivity(idAct)
    }

    override fun getTodayActivity(): List<Activity?> {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.set(year, month, day, 0, 0, 0)
        val todayEpoch = calendar.timeInMillis
        calendar.add(Calendar.MILLISECOND, 1000 * 60 * 60 * 24)
        val tomorrowEpoch = calendar.timeInMillis
        return activityDao.getTodayActivity(todayEpoch, tomorrowEpoch)
    }

    override fun getAllUndoneActivity(): List<Activity?> {
        return activityDao.getAllUndoneActivity()
    }
}