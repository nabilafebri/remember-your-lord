package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans

@Dao
interface EnTransDao {
    @Query("SELECT * FROM en_trans")
    fun fetchAllEnTrans(): LiveData<List<EnTrans>>

    @Query("SELECT * FROM en_trans WHERE ID = :id")
    fun getEnTrans(id: Int): LiveData<EnTrans>
}