package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quote")
public class Quote {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "title")
    private String quote;

    public Quote(String id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getQuote() {
        return quote;
    }
}
