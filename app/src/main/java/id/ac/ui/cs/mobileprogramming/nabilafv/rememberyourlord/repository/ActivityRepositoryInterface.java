package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;

public interface ActivityRepositoryInterface {
    void insertActivity(String title, String description, long date);

    LiveData<List<Activity>> getAllUndoneActivity();

    LiveData<List<Activity>> getTodayActivity();

    void updateActivity(String idAct);
}
