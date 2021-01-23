package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuranTextDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText;

@Database(entities = {QuranText.class}, version = 1, exportSchema = false)
public abstract class QuranTextDatabase extends RoomDatabase {

    private static QuranTextDatabase instance;

    public abstract QuranTextDao quranTextDao();

    public static synchronized QuranTextDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (QuranTextDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            QuranTextDatabase.class, "db_quran_text")
                            .createFromAsset("database/arabic.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}