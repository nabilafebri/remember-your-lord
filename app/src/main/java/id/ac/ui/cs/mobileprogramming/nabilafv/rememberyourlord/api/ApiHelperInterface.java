package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api;

import androidx.lifecycle.LiveData;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import retrofit2.Response;

public interface ApiHelperInterface {
    LiveData<Response<WeatherResponse>> getWeather(double lat, double lan);
}
