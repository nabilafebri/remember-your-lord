package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model.IdTrans;

@Dao
public interface IdTransDao {

    @Query("SELECT * FROM id_trans")
    LiveData<List<IdTrans>> fetchAllIdTranss();

    @Query("SELECT * FROM id_trans WHERE ID = :id")
    LiveData<IdTrans> getIdTrans(int id);
}
