package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.ActivityDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.WeatherDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Weather;

@Database(entities = {Activity.class, Weather.class}, version = 1, exportSchema = false)
public abstract class RememberYourLordDatabase extends RoomDatabase {
    public abstract ActivityDao activityDao();
    public abstract WeatherDao weatherDao();
}
