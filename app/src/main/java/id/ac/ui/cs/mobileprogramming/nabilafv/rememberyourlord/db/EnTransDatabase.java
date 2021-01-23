package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.EnTransDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans;

@Database(entities = {EnTrans.class}, version = 1, exportSchema = false)
public abstract class EnTransDatabase extends RoomDatabase {

    private static EnTransDatabase instance;

    public abstract EnTransDao enTransDao();

    public static synchronized EnTransDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (EnTransDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            EnTransDatabase.class, "db_en_trans")
                            .createFromAsset("database/en.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
