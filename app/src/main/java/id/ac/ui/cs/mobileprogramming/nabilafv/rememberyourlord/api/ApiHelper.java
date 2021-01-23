package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api;

import javax.inject.Inject;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import retrofit2.Response;

public class ApiHelper implements ApiHelperInterface {
    private ApiService apiService;

    @Inject
    public ApiHelper(ApiService apiService) {
        this.apiService = apiService;
    }

    public Response<WeatherResponse> getWeather(double lat, double lon) {
        return apiService.getWeather(lat, lon, "8ff2ad59864e1cb27c042fe9d0d75cb3");
    }
}
