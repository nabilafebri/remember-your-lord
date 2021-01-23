package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather")
public class Weather {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "lat")
    private double lat;

    @NonNull
    @ColumnInfo(name = "lon")
    private double lon;

    @NonNull
    @ColumnInfo(name = "temp")
    private double temp;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;

    public Weather(String id, double lat, double lon, double temp, String location) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.temp = temp;
        this.location = location;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getTemp() {
        return temp;
    }

    @NonNull
    public String getLocation() {
        return location;
    }
}
