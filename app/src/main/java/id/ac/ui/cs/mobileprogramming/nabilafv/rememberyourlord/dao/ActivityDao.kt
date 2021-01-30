package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: Activity)

    @Query("UPDATE activity SET is_done=1 WHERE id = :idAct")
    suspend fun updateActivity(idAct: String)

    @Query("SELECT * FROM activity WHERE is_done=0 AND date BETWEEN :startTime AND :endTime")
    fun getTodayActivity(startTime: Long, endTime: Long): List<Activity?>

    @Query("SELECT * FROM activity WHERE is_done=0")
    fun getAllUndoneActivity(): List<Activity?>
}