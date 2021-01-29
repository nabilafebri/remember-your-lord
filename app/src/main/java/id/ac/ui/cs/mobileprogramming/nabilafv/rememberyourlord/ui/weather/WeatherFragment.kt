package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.FragmentWeatherBinding
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var _binding: FragmentWeatherBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireView().context)

        weatherViewModel.getIsConnectedToNetwork().observe(viewLifecycleOwner) { item ->
            if (item) {
                checkLocationPermission(view.context)
            } else {
                binding.weatherWarning.text = getString(R.string.weather_warning)
            }
        }

        val connectivityManager =
            view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        weatherViewModel.updateNetwork(!connectivityManager.isActiveNetworkMetered)

        weatherViewModel.getWeatherResponse().observe(viewLifecycleOwner) { response ->
            if (response is State.Success) {
                hideLoading()
                if (response.data != null) {
                    binding.weatherWarning.text = ""
                    binding.weatherLocation.text = getString(
                        R.string.weather_location,
                        response.data.name
                    )
                    binding.weatherTemperature.text = getString(
                        R.string.weather_temperature,
                        response.data.main.temp
                    )
                }
            } else if (response is State.Loading) {
                showLoading()
            } else if (response is State.Failed) {
                Toast.makeText(context, getString(R.string.weather_failed), Toast.LENGTH_LONG)
                    .show()
            } else if (response is State.Initialize) {
                hideLoading()
            }
        }
    }

    private val requestPermissions = registerForActivityResult(RequestMultiplePermissions())
    { isGranted ->
        if (!isGranted.containsValue(false)) {
            getCurrentLocation()
        }
    }

    private fun checkLocationPermission(context: Context) {
        when {
            ((PermissionChecker.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED)
                    || (PermissionChecker.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED))
            -> {
                getCurrentLocation()
            }
            shouldShowRequestPermissionRationale("To get weather info") -> {
            }
            else -> {
                val locationPermissions = arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                requestPermissions.launch(locationPermissions)
            }
        }
    }

    fun hideLoading() {
        binding.weatherLoading.visibility = View.GONE
    }

    fun showLoading() {
        binding.weatherLoading.visibility = View.VISIBLE
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                weatherViewModel.fetchWeatherInfo(location.latitude, location.longitude)
            }
        }
    }
}