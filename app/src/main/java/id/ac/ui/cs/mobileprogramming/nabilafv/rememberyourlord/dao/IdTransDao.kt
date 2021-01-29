package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans

@Dao
interface IdTransDao {
    @Query("SELECT * FROM id_trans")
    fun fetchAllIdTrans(): LiveData<List<IdTrans>>

    @Query("SELECT * FROM id_trans WHERE ID = :id")
    fun getIdTrans(id: Int): LiveData<IdTrans>
}