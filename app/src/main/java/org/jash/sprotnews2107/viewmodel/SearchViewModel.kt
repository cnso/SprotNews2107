package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.sprotnews2107.dao.NewsDao
import org.jash.sprotnews2107.entity.News

class SearchViewModel(val newsDao: NewsDao):BaseViewModel() {
    val newsListLiveData by lazy { MutableLiveData<List<News>>() }
    fun searchNews(key: String) {
        val disposable = newsDao.search("%$key%").subscribe({
            newsListLiveData.postValue(it)
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable.takeIf { it.isDisposed }?.dispose() }
    }
}