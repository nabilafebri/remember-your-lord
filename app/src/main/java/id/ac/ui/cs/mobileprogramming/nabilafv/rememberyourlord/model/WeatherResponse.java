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

class Coordinate {
    private double lon;
    private double lat;

    public Coordinate(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }
}

class WeatherInfo {
    private String main;
    private String description;
    private String icon;

    public WeatherInfo(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}

class WeatherDetail {
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private double pressure;
    private double humidity;

    public WeatherDetail(double temp, double feelsLike, double tempMin, double tempMax,
                         double pressure, double humidity) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }
}

class Wind {
    private double speed;
    private double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}

class Clouds {
    private double all;

    public Clouds(double all) {
        this.all = all;
    }

    public double getAll() {
        return all;
    }
}