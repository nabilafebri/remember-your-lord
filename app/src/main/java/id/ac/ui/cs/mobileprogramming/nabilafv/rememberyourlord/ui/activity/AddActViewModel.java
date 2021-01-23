package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepository;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State;

public class AddActViewModel extends ViewModel {
    private ActivityRepository activityRepository;
    private MutableLiveData<State<Activity>> activity = new MutableLiveData<State<Activity>>();
    private MutableLiveData<Boolean> isInsert = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> isDatePicked = new MutableLiveData<Boolean>();

    @ViewModelInject
    public AddActViewModel(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
        activity.setValue(new State<Activity>().init());
        isInsert.setValue(false);
        isDatePicked.setValue(false);
    }

    public LiveData<State<Activity>> getActivity() {
        return activity;
    }

    public LiveData<Boolean> getIsInsert() {
        return isInsert;
    }

    public LiveData<Boolean> getIsDatePicked() {
        return isDatePicked;
    }

    public void setIsDatePicked() {
        isDatePicked.setValue(true);
    }

    public void addActivity(String title, String description, long date) {
        isInsert.setValue(true);
        activity.setValue(new State<Activity>().loading());
        new Thread(new Runnable() {
            public void run() {
                activityRepository.insertActivity(title, description, date);
            }
        }).start();
    }

    public void finishAddingToCalendar() {
        activity.setValue(new State<Activity>().success(null));
    }
}
