package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "quran_text")
public class QuranText implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;

    @NonNull
    @ColumnInfo(name = "SuraID")
    private int sura;

    @NonNull
    @ColumnInfo(name = "AyaID")
    private int aya;

    @NonNull
    @ColumnInfo(name = "Text")
    private String text;

    public QuranText(int id, int sura, int aya, String text) {
        this.id = id;
        this.sura = sura;
        this.aya = aya;
        this.text = text;
    }

    public int getId() { return id; }

    public int getSura() { return sura; }

    public int getAya() { return aya; }

    public String getText() { return text; }
}
