package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model.QuranText;

@Dao
public interface QuranTextDao {

    @Query("SELECT * FROM quran_text")
    LiveData<List<QuranText>> fetchAllQuranTexts();

    @Query("SELECT * FROM quran_text WHERE ID = :id")
    LiveData<QuranText> getQuranText(int id);
}
