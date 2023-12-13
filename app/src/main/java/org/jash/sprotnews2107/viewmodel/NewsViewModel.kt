package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.net.NewsService

class NewsViewModel:BaseViewModel() {
    val categoriesLiveData by lazy { MutableLiveData<List<Category>>() }
    val service by lazy { retrofit.create(NewsService::class.java) }
    fun loadCategory() {
        val disposable = service.getAllCategory().subscribe(
            {
                if (it.code == 0) {
                    categoriesLiveData.postValue(it.data)
                } else {
                    errorLiveData.postValue(it.msg)
                }
            }, {
                errorLiveData.postValue(it.message)
            }
        )
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }
}