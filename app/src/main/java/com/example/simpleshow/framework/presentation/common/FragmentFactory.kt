package com.example.simpleshow.framework.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.simpleshow.framework.presentation.splash.SplashFragment
import com.example.simpleshow.framework.presentation.weather.WeatherFragment
import javax.inject.Inject

class FragmentFactoryImpl @Inject constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SplashFragment::class.java.name -> SplashFragment()
            WeatherFragment::class.java.name -> WeatherFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}