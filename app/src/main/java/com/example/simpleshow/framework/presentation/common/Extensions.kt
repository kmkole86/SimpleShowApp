package com.example.simpleshow.framework.presentation.common

import android.view.View

fun View.animateToVisible() {
    if (alpha == 1f) return
    animate().alpha(1f)
        .apply {
            withStartAction {
                visibility = View.VISIBLE
                alpha = 0.5f
            }
        }.start()
}

fun View.animateToInvisible() {
    if (alpha == 0f) return
    animate().alpha(0f).apply {
        withStartAction { visibility = View.VISIBLE }
        withEndAction { visibility = View.INVISIBLE }
    }.start()
}