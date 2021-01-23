package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.ActivityDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;

public class ActivityRepository implements ActivityRepositoryInterface {
    private ActivityDao activityDao;

    @Inject
    public ActivityRepository(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void insertActivity(String title, String description, long date) {
        Activity activity = new Activity(UUID.randomUUID().toString(), title, description,
                false, date);
        new InsertActivityAsyncTask(activityDao).execute(activity);
    }

    private static class InsertActivityAsyncTask extends AsyncTask<Activity, Void, Void> {
        private ActivityDao activityDao;
        private InsertActivityAsyncTask(ActivityDao activityDao) {
            this.activityDao = activityDao;
        }

        @Override
        protected Void doInBackground(Activity... activities) {
            activityDao.insertActivity(activities[0]);
            return null;
        }
    }

    public List<Activity> getAllUndoneActivity() {return activityDao.getAllUndoneActivity();}

    public List<Activity> getTodayActivity() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year, month, day, 0, 0, 0);
        long todayEpoch = calendar.getTimeInMillis();

        calendar.add(Calendar.MILLISECOND, 1000 * 60 * 60 * 24);
        long tomorrowEpoch = calendar.getTimeInMillis();

        return activityDao.getTodayActivity(todayEpoch, tomorrowEpoch);
    }

    public void updateActivity(String idAct) {
        new UpdateActivityAsyncTask(activityDao).execute(idAct);
    }

    private static class UpdateActivityAsyncTask extends AsyncTask<String, Void, Void> {
        private ActivityDao activityDao;
        private UpdateActivityAsyncTask(ActivityDao activityDao) {
            this.activityDao = activityDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            activityDao.updateActivity(strings[0]);
            return null;
        }
    }
}
