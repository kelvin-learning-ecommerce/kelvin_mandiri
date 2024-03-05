package com.kelvin.mandiri.presentation.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.mandiri.presentation.home.HomePageActivity
import com.kelvin.mandiri.presentation.utils.freshStartActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {


    fun toHomePage(ctx: Context) {
        viewModelScope.launch {

            delay(2000)

            ctx.freshStartActivity(HomePageActivity::class.java)
        }
    }
}
