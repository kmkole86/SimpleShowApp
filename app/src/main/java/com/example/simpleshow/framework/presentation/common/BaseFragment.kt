package com.example.simpleshow.framework.presentation.common

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun setActionBarVisible(visible: Boolean) {
        val parentActivity = requireActivity() as? BaseActivity
        parentActivity?.let { it.setActionBarVisible(visible) }
    }
}