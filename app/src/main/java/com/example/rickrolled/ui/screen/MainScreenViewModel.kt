package com.example.rickrolled.ui.screen

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chuckerteam.chucker.api.ChuckerCollector
import com.example.rickrolled.utils.ConnectivityObserver
import com.example.rickrolled.utils.NetworkConnectivityObserver
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val networkObserver: NetworkConnectivityObserver,
    val chuckerCollector: ChuckerCollector
) : ViewModel() {
    private val networkFlow = networkObserver.observe()
    val state = MutableTransitionState(false).apply { targetState = true }
    val isNetworkAvailable = mutableStateOf(false)

    init {
        viewModelScope.launch {
            networkFlow.collect { networkStatus ->
                state.targetState = networkStatus == ConnectivityObserver.Status.Lost
                isNetworkAvailable.value = networkStatus == ConnectivityObserver.Status.Available
            }
        }
    }
}