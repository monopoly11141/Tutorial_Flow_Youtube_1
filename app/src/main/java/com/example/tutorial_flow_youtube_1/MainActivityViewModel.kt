package com.example.tutorial_flow_youtube_1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val countDownFlow = flow<Int>() {
        val startValue = 10
        var currentValue = startValue

        emit(currentValue)
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }

}