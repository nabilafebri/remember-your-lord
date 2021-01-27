package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api;

import androidx.lifecycle.LiveData;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    LiveData<Response<WeatherResponse>> getWeather(@Query("lat") double lat,
                                                  @Query("lon") double lon,
                                                  @Query("appid") String apiKey);
}
