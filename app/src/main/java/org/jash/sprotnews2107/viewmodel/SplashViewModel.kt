package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import java.util.Timer
import java.util.TimerTask

class SplashViewModel:BaseViewModel() {
    val countLiveData by lazy { MutableLiveData<Int>() }
    var count = 3
    val timer:Timer = Timer()
    fun start() {
        timer.schedule(object :TimerTask(){
            override fun run() {
                countLiveData.postValue(count--)
                if(count < 0) {
                    timer.cancel()
                }
            }
        }, 1000, 1000)
    }
}