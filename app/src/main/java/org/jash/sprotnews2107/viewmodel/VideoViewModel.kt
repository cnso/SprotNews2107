package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.entity.Page
import org.jash.sprotnews2107.entity.Video
import org.jash.sprotnews2107.net.NewsService

class VideoViewModel:BaseViewModel() {
    val service by lazy { retrofit.create(NewsService::class.java) }
    val videoLiveData by lazy { (MutableLiveData<Page<Video>>()) }
    fun loadVideo(page:Int, size:Int) {
        val disposable = service.getVideo(page, size).subscribe(
            {
                if (it.code == 0) {
                    videoLiveData.postValue(it.data)
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