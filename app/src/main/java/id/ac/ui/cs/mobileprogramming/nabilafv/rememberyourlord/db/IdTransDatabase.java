package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.IdTransDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans;

@Database(entities = {IdTrans.class}, version = 1, exportSchema = false)
public abstract class IdTransDatabase extends RoomDatabase {

    private static IdTransDatabase instance;

    public abstract IdTransDao idTransDao();

    public static synchronized IdTransDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (IdTransDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            IdTransDatabase.class, "db_id_trans")
                            .createFromAsset("database/id.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}