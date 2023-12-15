package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.net.NewsService

class MineViewModel:BaseViewModel() {
    val service by lazy { retrofit.create(NewsService::class.java) }
    val logoutLiveData by lazy { MutableLiveData<String>() }
    fun logout() {
        val disposable = service.logout().subscribe({
            if (it.code == 0) {
                logoutLiveData.postValue("退出成功")
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }
}