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
class AddActViewModel @Inject constructor(private val activityRepository: ActivityRepository) :
    ViewModel() {
    private val activity = MutableLiveData<State<Activity>>()
    private val isInsert = MutableLiveData<Boolean>()
    private val isDatePicked = MutableLiveData<Boolean>()

    fun getActivity(): LiveData<State<Activity>> {
        return activity
    }

    fun getIsInsert(): LiveData<Boolean> {
        return isInsert
    }

    fun getIsDatePicked(): LiveData<Boolean> {
        return isDatePicked
    }

    fun setIsDatePicked() {
        isDatePicked.value = true
    }

    fun addActivity(title: String, description: String, date: Long) {
        isInsert.value = true
        activity.setValue(State<Activity>().loading())
        viewModelScope.launch(Dispatchers.IO) {
            activityRepository.insertActivity(title, description, date)
        }
    }

    fun finishAddingToCalendar() {
        activity.setValue(State<Activity>().success(null))
    }

    init {
        activity.setValue(State<Activity>().init())
        isInsert.value = false
        isDatePicked.value = false
    }
}