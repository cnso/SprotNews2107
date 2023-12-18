package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.net.NewsService

class PhoneViewModel:BaseViewModel() {
    val service by lazy { retrofit.create(NewsService::class.java) }
    val tokenLiveData by lazy { MutableLiveData<String>() }
    val codeLiveData by lazy { MutableLiveData<Int>() }
    fun loginForPhone(user:Map<String, String>) {
        val disposable = service.loginForPhone(user).subscribe({
            if (it.code == 0) {
                tokenLiveData.postValue(it.data)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }
    fun getCode(phone:String) {
        val disposable = service.getLoginCode(phone).subscribe({
            if (it.code == 0) {
                codeLiveData.postValue(it.data)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }
}