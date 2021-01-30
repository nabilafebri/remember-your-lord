package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class Activity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @ColumnInfo(name = "date") val date: Long
)