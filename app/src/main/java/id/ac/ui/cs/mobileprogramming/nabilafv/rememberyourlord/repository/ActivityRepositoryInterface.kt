package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity


interface ActivityRepositoryInterface {
    suspend fun insertActivity(title: String, description: String, date: Long)
    suspend fun updateActivity(idAct: String)
    fun getTodayActivity(): LiveData<List<Activity>>
    fun getAllUndoneActivity(): LiveData<List<Activity>>
}