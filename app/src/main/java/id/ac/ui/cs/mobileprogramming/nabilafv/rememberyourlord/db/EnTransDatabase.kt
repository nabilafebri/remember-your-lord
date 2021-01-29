package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.EnTransDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans

@Database(entities = [EnTrans::class], version = 1, exportSchema = false)
abstract class EnTransDatabase : RoomDatabase() {
    abstract fun enTransDao(): EnTransDao
}