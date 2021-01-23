package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.di;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.ActivityDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuoteDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.WeatherDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Quote;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Weather;

@Database(entities = {Activity.class, Quote.class, Weather.class}, version = 1)
public abstract class RememberYourLordDatabase extends RoomDatabase {
    public abstract ActivityDao activityDao();
    public abstract QuoteDao quoteDao();
    public abstract WeatherDao weatherDao();
}
