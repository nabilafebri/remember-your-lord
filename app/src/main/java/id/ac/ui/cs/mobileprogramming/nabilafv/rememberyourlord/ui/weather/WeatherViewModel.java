package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.weather;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.WeatherRepository;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    private final WeatherRepository weatherRepository;
    private MutableLiveData<State<WeatherResponse>> weatherResponse = new MutableLiveData<State<WeatherResponse>>();
    private MutableLiveData<Boolean> isConnectedToNetwork = new MutableLiveData<Boolean>();

    @ViewModelInject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherResponse.postValue(new State<WeatherResponse>().init());
    }

    public LiveData<State<WeatherResponse>> getWeatherResponse() {
        return weatherResponse;
    }

    public LiveData<Boolean> getIsConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public void updateNetwork(Boolean isConnected) {
        isConnectedToNetwork.postValue(isConnected);
    }

    public void fetchWeatherInfo(double lat, double lon) {
        new Thread(new Runnable() {
            public void run() {
                weatherResponse.postValue(new State<WeatherResponse>().loading());

                Response<WeatherResponse> response = weatherRepository.getWeather(lat, lon);
                if (response != null) {
                    if (response.isSuccessful()) {
                        weatherResponse.postValue(new State<WeatherResponse>().success(response.body()));
                    }
                }
            }
        }).start();
    }
}
