package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.IdTransDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans

@Database(entities = [IdTrans::class], version = 1, exportSchema = false)
abstract class IdTransDatabase : RoomDatabase() {
    abstract fun idTransDao(): IdTransDao
}