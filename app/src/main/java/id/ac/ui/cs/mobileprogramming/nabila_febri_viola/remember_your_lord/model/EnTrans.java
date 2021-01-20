package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "en_trans")
public class EnTrans implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "SuraID")
    @NonNull
    private int sura;

    @ColumnInfo(name = "AyaID")
    @NonNull
    private int aya;

    @ColumnInfo(name = "Text")
    @NonNull
    private String text;

    public EnTrans(int id, int sura, int aya, String text) {
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