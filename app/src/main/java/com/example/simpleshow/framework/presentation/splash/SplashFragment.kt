package com.example.simpleshow.framework.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.simpleshow.R
import com.example.simpleshow.databinding.FragmentSplashBinding
import com.example.simpleshow.framework.presentation.common.BaseFragment
import com.example.simpleshow.framework.presentation.common.animateToInvisible
import com.example.simpleshow.framework.presentation.common.animateToVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()

    private var _binding: FragmentSplashBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                when (state) {
                    is SplashViewState.Idle -> {
                        //do nothing, for now
                    }
                    is SplashViewState.Loading -> {
                        binding.fragSplashProgressBar.animateToVisible()
                    }
                    is SplashViewState.Error -> {
                        //TODO show error
                        binding.fragSplashProgressBar.animateToInvisible()
                    }
                    is SplashViewState.NavigateToWeatherPage -> navigateToWeatherFragment()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToWeatherFragment() {
        val action = SplashFragmentDirections.actionNavigateToWeatherFragment()
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
        findNavController().navigate(action, navOptions)
    }
}