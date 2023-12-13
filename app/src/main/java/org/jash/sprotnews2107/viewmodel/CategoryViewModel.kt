package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.entity.Page
import org.jash.sprotnews2107.net.NewsService

class CategoryViewModel: BaseViewModel() {
    val newsPageLiveData by lazy { MutableLiveData<Page<News>>() }
    val service by lazy { retrofit.create(NewsService::class.java) }
    fun loadNewsPage(type:Int, page:Int, size:Int) {
        val disposable = service.getNewsByCategoryId(type, page, size)
            .subscribe({
                if (it.code == 0) {
                    newsPageLiveData.postValue(it.data)
                } else {
                    errorLiveData.postValue(it.msg)
                }
            }, {
                errorLiveData.postValue(it.message)
            })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }

}