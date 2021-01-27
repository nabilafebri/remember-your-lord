package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;

@Dao
public interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertActivity(Activity activity);

    @Query("UPDATE activity SET is_done=1 WHERE id = :idAct")
    void updateActivity(String idAct);

    @Query("SELECT * FROM activity WHERE is_done=0 AND date BETWEEN :startTime AND :endTime")
    LiveData<List<Activity>> getTodayActivity(Long startTime, Long endTime);

    @Query("SELECT * FROM activity WHERE is_done=0")
    LiveData<List<Activity>> getAllUndoneActivity();
}
