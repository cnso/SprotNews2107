package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.dao.NewsDao
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.net.NewsService

class DetailViewModel(val newsDao: NewsDao):BaseViewModel() {
    val newsLiveData by lazy { MutableLiveData<News>() }
    val service by lazy { retrofit.create(NewsService::class.java) }
    fun loadNews(id:Int) {
        val disposable = newsDao.findById(id).subscribe({
            newsLiveData.postValue(it)
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
        val disposable1 = service.getNewsDetail(id).subscribe({
            if (it.code == 0) {
                newsLiveData.postValue(it.data)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable1.takeIf { it.isDisposed }?.dispose() }

    }
}