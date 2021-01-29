package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelperInterface {
    override suspend fun getWeather(lat: Double, lon: Double): Response<WeatherResponse> {
        return apiService.getWeather(lat, lon, "8ccdc22500f2bb515caf8f79632b9df5")
    }
}