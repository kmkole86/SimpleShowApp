package com.example.simpleshow.framework.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.simpleshow.R
import com.example.simpleshow.business.domain.util.DateUtil
import com.example.simpleshow.databinding.FragmentWeatherBinding
import com.example.simpleshow.framework.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : BaseFragment() {

    private val viewModel: WeatherViewModel by viewModels()

    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarVisible(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.fragWeatherBtRefresh.setOnClickListener { viewModel.onAction(ActionUpdateWeatherData) }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect { state ->

                binding.fragWeatherBtRefresh.isEnabled = !state.isLoading
                binding.fragWeatherProgressBar.visibility =
                    if (state.isLoading) View.VISIBLE else View.INVISIBLE

                val dateUtil = DateUtil()

                state.weatherData?.let {
                    binding.fragWeatherTvLastUpdateTime.text =
                        dateUtil.getUnixTimestamp(it.updatedAt)
                    binding.fragWeatherTvTemp.text =
                        getString(R.string.temp_celsius_printout, it.temperature)
                    binding.fragWeatherTvTempFeelsLike.text =
                        getString(R.string.temp_celsius_printout, it.temperatureFeelsLike)
                    binding.fragWeatherTvWindSpeed.text =
                        getString(R.string.wind_speed_printout, it.windSpeed)
                    binding.fragWeatherTvPressure.text =
                        getString(R.string.pressure_printout, it.pressure)
                    binding.fragWeatherTvHumidity.text =
                        getString(R.string.humid_printout, it.humidity)
                    binding.fragWeatherTvMinMaxTemp.text =
                        getString(
                            R.string.temp_min_max_celsius_printout,
                            it.temperatureMin,
                            it.temperatureMax
                        )

                    Glide.with(this@WeatherFragment)
                        .load(getString(R.string.icon_url, it.iconUrl)) // refactor to helper
                        .centerCrop().into(binding.imageView)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}