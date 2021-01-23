package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activity")
public class Activity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "is_done")
    private boolean isDone;

    @NonNull
    @ColumnInfo(name = "date")
    private long date;

    public Activity(String id, String title, String description, boolean isDone, long date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public boolean getIsDone() {
        return isDone;
    }

    @NonNull
    public long getDate() {
        return date;
    }
}