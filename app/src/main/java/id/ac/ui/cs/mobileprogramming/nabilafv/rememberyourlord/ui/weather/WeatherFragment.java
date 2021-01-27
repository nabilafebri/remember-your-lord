package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.WeatherResponse;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {
    WeatherViewModel weatherViewModel;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView textViewTitle;
    private TextView textViewWarning;
    private TextView textViewLocation;
    private TextView textViewTemp;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        textViewTitle = view.findViewById(R.id.weather_title);
        textViewWarning = view.findViewById(R.id.weather_warning);
        textViewLocation = view.findViewById(R.id.weather_location);
        textViewTemp = view.findViewById(R.id.weather_temperature);
        progressBar = view.findViewById(R.id.weather_loading);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireView().getContext());
        weatherViewModel.getIsConnectedToNetwork().observe(getViewLifecycleOwner(), item -> {
            if (item) {
                checkLocationPermission(view.getContext());
            } else {
                textViewWarning.setText(getString(R.string.weather_warning));
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        weatherViewModel.updateNetwork(!connectivityManager.isActiveNetworkMetered());

        weatherViewModel.getWeatherResponse().observe(getViewLifecycleOwner(), response -> {
            if (response instanceof State.Success) {
                hideLoading();
                if (((State.Success) response).data != null) {
                    textViewWarning.setText("");
                    textViewLocation.setText(getString(R.string.weather_location,
                            ((WeatherResponse) ((State.Success) response).data).getName()));
                    textViewTemp.setText(getString(R.string.weather_temperature,
                            ((WeatherResponse) ((State.Success) response).data).getMain().getTemp()));
                }
            } else if (response instanceof State.Loading) {
                showLoading();
            } else if (response instanceof State.Failed) {
                Toast.makeText(getContext(), getString(R.string.weather_failed), Toast.LENGTH_LONG)
                        .show();
            } else if (response instanceof State.Initialize) {
                hideLoading();
            };
        });
    }
    private ActivityResultLauncher<String[]> requestPermissions =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                if (!isGranted.containsValue(false)) {
                    getCurrentLocation();
                }
            });

    public void checkLocationPermission(Context context) {
        if ((PermissionChecker.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PermissionChecker.PERMISSION_GRANTED) &&
                (PermissionChecker.checkSelfPermission(
                        context, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PermissionChecker.PERMISSION_GRANTED)) {
            getCurrentLocation();
        } else {
            String[] locationPermissions = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions.launch(locationPermissions);
        }
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    weatherViewModel.fetchWeatherInfo(location.getLatitude(), location.getLongitude());
                }
            }
        });
    }
}
