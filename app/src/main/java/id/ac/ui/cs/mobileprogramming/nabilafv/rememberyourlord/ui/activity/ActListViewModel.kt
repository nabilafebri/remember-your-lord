package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepository
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActListViewModel @Inject constructor(private val activityRepository: ActivityRepository) :
    ViewModel() {
    private val selectedActivity = MutableLiveData<Activity>()
    private val allUndoneActivity = MutableLiveData<State<List<Activity>>>()

    fun getSelectedActivity(): LiveData<Activity> {
        return selectedActivity
    }

    fun getAllUndoneActivity(): LiveData<State<List<Activity>>> {
        return allUndoneActivity
    }

    private fun getAllUndoneActivityAsync() {
        viewModelScope.launch(Dispatchers.IO) {
            allUndoneActivity.postValue(State<List<Activity>>().loading())
            val undoneActivity = activityRepository.getAllUndoneActivity()
            allUndoneActivity.postValue(State<List<Activity>>().success(undoneActivity.value))
        }
    }

    fun selectActivity(selected: Activity) {
        selectedActivity.postValue(selected)
    }

    fun checkActivity(selected: Activity) {
        viewModelScope.launch(Dispatchers.IO) {
            activityRepository.updateActivity(selected.id)
        }
    }

    init {
        allUndoneActivity.postValue(State<List<Activity>>().init())
        getAllUndoneActivityAsync()
    }
}