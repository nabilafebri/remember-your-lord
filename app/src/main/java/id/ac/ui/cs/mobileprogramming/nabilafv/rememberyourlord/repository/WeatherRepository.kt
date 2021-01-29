package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiHelper
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.WeatherDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Weather
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val apiHelper: ApiHelper
) :
    WeatherRepositoryInterface {
    override suspend fun getWeather(lat: Double, lon: Double): Response<WeatherResponse> {
        return apiHelper.getWeather(lat, lon)
    }

    override suspend fun insertWeather(lat: Double, lon: Double, temp: Double, location: String) {
        weatherDao.insertWeather(Weather(UUID.randomUUID().toString(), lat, lon, temp, location))
    }
}