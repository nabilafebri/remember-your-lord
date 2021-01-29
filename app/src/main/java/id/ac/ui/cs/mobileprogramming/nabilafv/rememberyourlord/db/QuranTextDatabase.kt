package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuranTextDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText

@Database(entities = [QuranText::class], version = 1, exportSchema = false)
abstract class QuranTextDatabase : RoomDatabase() {
    abstract fun quranTextDao(): QuranTextDao
}