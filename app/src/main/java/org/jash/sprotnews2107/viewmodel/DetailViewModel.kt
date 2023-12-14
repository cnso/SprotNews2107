package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.sprotnews2107.dao.NewsDao
import org.jash.sprotnews2107.entity.News

class DetailViewModel(val newsDao: NewsDao):BaseViewModel() {
    val newsLiveData by lazy { MutableLiveData<News>() }
    fun loadNews(id:Int) {
        val disposable = newsDao.findById(id).subscribe({
            newsLiveData.postValue(it)
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }
}