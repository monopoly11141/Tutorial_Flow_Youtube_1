package com.example.tutorial_flow_youtube_1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

    init {
        collectFlow()
    }

    private fun collectFlow() {

        val exampleFlow = flow{
            emit("a")
            delay(500)
            emit("b")
        }

        viewModelScope.launch {

            exampleFlow.flatMapConcat {str ->
                flow {
                    emit(str + "c")
                    delay(500)
                    emit(str + "d")
                }
            }.collect() {str ->
                Log.d("MainActivityViewModel","String $str")
            }

//            val foldResult = countDownFlow.fold(100){ accumulator, value ->
//                accumulator - value
//            }
//            Log.d("MainActivityViewModel", "ReduceResult : $foldResult")
//
//            countDownFlow
//                .filter{time ->
//                    time % 2 == 0
//                }
//                .map{ time ->
//                    time * time
//                }
//                .collect { time ->
//                    delay(1000)
//                    Log.d("MainActivityViewModel", "The Current Time is $time")
//                }
        }
    }

}