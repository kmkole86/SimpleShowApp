package com.example.simpleshow.framework.presentation

import android.os.Bundle
import com.example.simpleshow.databinding.ActivityMainBinding
import com.example.simpleshow.framework.presentation.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainActivityToolbar)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}