package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse
import retrofit2.Response

interface ApiHelperInterface {
    suspend fun getWeather(lat: Double, lon: Double): Response<WeatherResponse>
}