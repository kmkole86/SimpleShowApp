package com.example.simpleshow.framework.presentation.splash

import com.example.simpleshow.business.domain.store.ViewStateStore
import javax.inject.Inject

class SplashStore @Inject constructor() :
    ViewStateStore<SplashViewState>(SplashViewState())