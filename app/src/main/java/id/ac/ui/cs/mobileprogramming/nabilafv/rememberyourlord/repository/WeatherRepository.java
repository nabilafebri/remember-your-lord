package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import android.os.AsyncTask;

import java.util.UUID;

import javax.inject.Inject;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiHelper;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.WeatherDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Weather;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import retrofit2.Response;

public class WeatherRepository implements WeatherRepositoryInterface {
    private WeatherDao weatherDao;
    private ApiHelper apiHelper;

    @Inject
    public WeatherRepository(WeatherDao weatherDao, ApiHelper apiHelper) {
        this.weatherDao = weatherDao;
        this.apiHelper = apiHelper;
    }

    public Response<WeatherResponse> getWeather(double lat, double lon) {
        return apiHelper.getWeather(lat, lon);
    }

    public void insertWeather(double lat, double lon, double temp, String location) {
        Weather weather = new Weather(UUID.randomUUID().toString(), lat, lon, temp, location);
        new InsertWeatherAsyncTask(weatherDao).execute(weather);
    }

    private static class InsertWeatherAsyncTask extends AsyncTask<Weather, Void, Void> {
        private WeatherDao weatherDao;
        private InsertWeatherAsyncTask(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(Weather... weathers) {
            weatherDao.insertWeather(weathers[0]);
            return null;
        }
    }
}
