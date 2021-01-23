package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans;

@Dao
public interface EnTransDao {

    @Query("SELECT * FROM en_trans")
    LiveData<List<EnTrans>> fetchAllEnTrans();

    @Query("SELECT * FROM en_trans WHERE ID = :id")
    LiveData<EnTrans> getEnTrans(int id);
}
