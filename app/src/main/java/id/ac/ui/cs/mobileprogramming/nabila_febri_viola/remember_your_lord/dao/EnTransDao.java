package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model.EnTrans;

@Dao
public interface EnTransDao {

    @Query("SELECT * FROM en_trans")
    LiveData<List<EnTrans>> fetchAllEnTranss();

    @Query("SELECT * FROM en_trans WHERE ID = :id")
    LiveData<EnTrans> getEnTrans(int id);
}
