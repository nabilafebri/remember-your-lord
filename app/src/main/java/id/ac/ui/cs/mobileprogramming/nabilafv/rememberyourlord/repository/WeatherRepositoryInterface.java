package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import androidx.lifecycle.LiveData;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import retrofit2.Response;

public interface WeatherRepositoryInterface {

    LiveData<Response<WeatherResponse>> getWeather(double lat, double lon);

    void insertWeather(double lat, double lon, double temp, String location);
}
