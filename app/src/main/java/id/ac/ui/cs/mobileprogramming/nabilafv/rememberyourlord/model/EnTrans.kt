package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "en_trans")
data class EnTrans(
    @PrimaryKey @ColumnInfo(name = "ID") val id: Int,
    @ColumnInfo(name = "SuraID") val sura: Int,
    @ColumnInfo(name = "AyaID") val aya: Int,
    @ColumnInfo(name = "Text") val text: String
)