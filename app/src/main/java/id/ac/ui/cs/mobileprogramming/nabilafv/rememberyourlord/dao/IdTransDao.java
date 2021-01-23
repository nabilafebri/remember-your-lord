package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans;

@Dao
public interface IdTransDao {

    @Query("SELECT * FROM id_trans")
    LiveData<List<IdTrans>> fetchAllIdTrans();

    @Query("SELECT * FROM id_trans WHERE ID = :id")
    LiveData<IdTrans> getIdTrans(int id);
}
