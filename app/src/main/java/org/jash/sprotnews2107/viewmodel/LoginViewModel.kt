package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.net.NewsService

class LoginViewModel:BaseViewModel() {
    val service by lazy { retrofit.create(NewsService::class.java) }
    val tokenLiveData by lazy { MutableLiveData<String>() }
    fun login(user:Map<String, String>) {
        val disposable = service.login(user).subscribe({
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
}