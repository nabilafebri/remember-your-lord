package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.WeatherRepository
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val weatherResponse = MutableLiveData<State<WeatherResponse>>()
    private val isConnectedToNetwork = MutableLiveData<Boolean>()

    fun getWeatherResponse(): LiveData<State<WeatherResponse>> {
        return weatherResponse
    }

    fun getIsConnectedToNetwork(): LiveData<Boolean> {
        return isConnectedToNetwork
    }

    fun updateNetwork(isConnected: Boolean) {
        isConnectedToNetwork.postValue(isConnected)
    }

    fun fetchWeatherInfo(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherResponse.postValue(State<WeatherResponse>().loading())
            weatherRepository.getWeather(lat, lon).let { response ->
                if (response.isSuccessful) {
                    weatherResponse.postValue(State<WeatherResponse>().success(response.body()))
                }
            }
        }
    }

    init {
        weatherResponse.postValue(State<WeatherResponse>().init())
    }
}