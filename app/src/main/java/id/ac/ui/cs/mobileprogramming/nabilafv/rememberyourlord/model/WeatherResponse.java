package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model;

import java.util.List;

public class WeatherResponse {
    private Coordinate coord;
    private List<WeatherInfo> weather;
    private String base;
    private WeatherDetail main;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private int timezone;
    private long id;
    private String name;

    public WeatherResponse(Coordinate coord,
                           List<WeatherInfo> weather,
                           String base,
                           WeatherDetail main,
                           Wind wind,
                           Clouds clouds,
                           long dt,
                           int timezone,
                           long id,
                           String name) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public List<WeatherInfo> getWeather() {
        return this.weather;
    }

    public String getBase() {
        return this.base;
    }

    public WeatherDetail getMain() {
        return this.main;
    }

    public Wind getWind() {
        return this.wind;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public long getDt() {
        return this.dt;
    }

    public int getTimezone() {
        return this.timezone;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}