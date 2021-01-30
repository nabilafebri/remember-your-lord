package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText

@Dao
interface QuranTextDao {
    @Query("SELECT * FROM quran_text")
    fun fetchAllQuranTexts(): List<QuranText>

    @Query("SELECT * FROM quran_text WHERE ID = :id")
    fun getQuranText(id: Int): QuranText
}