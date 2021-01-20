package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.dao.EnTransDao;
import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model.EnTrans;

@Database(entities = {EnTrans.class}, version = 1)
public abstract class EnTransDatabase extends RoomDatabase {

    private static EnTransDatabase instance;

    public abstract EnTransDao enTransDao();

    public static synchronized EnTransDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (EnTransDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            EnTransDatabase.class, "db_quran_text")
                            .createFromAsset("database/arabic.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
