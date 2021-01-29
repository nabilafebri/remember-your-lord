package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse
import retrofit2.Response


interface WeatherRepositoryInterface {
    suspend fun getWeather(lat: Double, lon: Double): Response<WeatherResponse>
    suspend fun insertWeather(lat: Double, lon: Double, temp: Double, location: String)
}