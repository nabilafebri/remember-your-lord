package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepository;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State;

public class ActListViewModel extends ViewModel {
    private ActivityRepository activityRepository;
    private MutableLiveData<Activity> selectedActivity = new MutableLiveData<Activity>();
    private MutableLiveData<State<List<Activity>>> allUndoneActivity = new MutableLiveData<State<List<Activity>>>();

    @ViewModelInject
    public ActListViewModel(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
        this.allUndoneActivity.postValue(new State<List<Activity>>().init());
        getAllUndoneActivityThread();
    }

    public ActListViewModel(){}

    private void getAllUndoneActivityThread() {
        new Thread(new Runnable() {
            public void run() {
                allUndoneActivity.postValue(new State<List<Activity>>().loading());
                List<Activity> undoneActivity = activityRepository.getAllUndoneActivity();
                allUndoneActivity.postValue(new State<List<Activity>>().success(undoneActivity));
            }
        }).start();
    }

    public LiveData<Activity> getSelectedActivity() {
        return selectedActivity;
    }

    public LiveData<State<List<Activity>>> getAllUndoneActivity() {
        return allUndoneActivity;
    }

    public void selectActivity(Activity selected) {
        selectedActivity.postValue(selected);
    }

    public void checkActivity(Activity selected) {
        new Thread(new Runnable() {
            public void run() {
                activityRepository.updateActivity(selected.getId());
            }
        }).start();
    }
}
