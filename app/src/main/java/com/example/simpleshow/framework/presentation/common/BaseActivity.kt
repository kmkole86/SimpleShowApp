package com.example.simpleshow.framework.presentation.common

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    internal fun setActionBarVisible(visible: Boolean) {
        supportActionBar?.let { if (visible) it.show() else it.hide() }
    }
}