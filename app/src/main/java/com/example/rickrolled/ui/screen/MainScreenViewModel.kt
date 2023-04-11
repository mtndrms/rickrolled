package com.example.rickrolled.ui.screen

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickrolled.utils.ConnectivityObserver
import com.example.rickrolled.utils.NetworkConnectivityObserver
import kotlinx.coroutines.launch

class MainScreenViewModel(private val networkObserver: NetworkConnectivityObserver) : ViewModel() {
    private val networkFlow = networkObserver.observe()
    private val state = MutableTransitionState(false).apply { targetState = true }
    private val isNetworkAvailable = mutableStateOf(false)

    init {
        viewModelScope.launch {
            networkFlow.collect { networkStatus ->
                state.targetState = networkStatus == ConnectivityObserver.Status.Lost
                isNetworkAvailable.value = networkStatus == ConnectivityObserver.Status.Available
            }
        }
    }
}