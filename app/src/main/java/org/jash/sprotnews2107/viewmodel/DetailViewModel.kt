package org.jash.sprotnews2107.viewmodel

import androidx.lifecycle.MutableLiveData
import org.jash.common.mvvm.BaseViewModel
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.dao.NewsDao
import org.jash.sprotnews2107.dao.NewsDatabase
import org.jash.sprotnews2107.dao.UserDao
import org.jash.sprotnews2107.entity.Comment
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.entity.User
import org.jash.sprotnews2107.net.NewsService

class DetailViewModel(newsDatabase: NewsDatabase):BaseViewModel() {
    val newsDao:NewsDao
    val userDao: UserDao

    val newsLiveData by lazy { MutableLiveData<News>() }
    val service by lazy { retrofit.create(NewsService::class.java) }
    val collectLiveData by lazy{ MutableLiveData<List<News>>() }
    val collectedLiveData by lazy{ MutableLiveData<String>() }
    val postCommentLiveData by lazy{ MutableLiveData<Boolean>() }
    val commentLiveData by lazy{ MutableLiveData<List<Comment>>() }
    init {
        newsDao = newsDatabase.getNewsDao()
        userDao = newsDatabase.getUserDao()
    }
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
    fun loadCollect() {
        val disposable1 = service.getMyCollect().subscribe({
            if (it.code == 0) {
                collectLiveData.postValue(it.data)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable1.takeIf { it.isDisposed }?.dispose() }

    }
    fun collect(id:Int){
        val disposable1 = service.collect(id).subscribe({
            if (it.code == 0) {
                collectedLiveData.postValue(it.data)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable1.takeIf { it.isDisposed }?.dispose() }
    }
    fun postComment(comment: Comment) {
        val disposable1 = service.postComment(comment).subscribe({
            if (it.code == 0) {
                postCommentLiveData.postValue(true)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable1.takeIf { it.isDisposed }?.dispose() }
    }
    fun updateUser() {
        val list = service.getUserAll1().zipWith(service.getUserAll2()) { r1, r2 ->
            if (r1.code == 0 && r2.code == 0) {
                r1.data.zip(r2.data) { u1, u2 ->
                    User(
                        u1.birthday ?: u2.birthday,
                        u1.createTime,
                        Math.max(u1.flag, u2.flag),
                        u1.id,
                        u1.imgurl ?: u2.imgurl,
                        u1.info ?: u2.info,
                        u1.password ?: u2.password,
                        u1.phone ?: u2.phone,
                        u1.sex ?: u2.sex,
                        u1.username ?: u2.username
                    )
                }
            } else {
                listOf<User>()
            }
        }.blockingFirst()
        userDao.save(*list.toTypedArray())
    }
    fun loadComment(nid:Int){
        val disposable1 = service.getCommentByNid(nid).subscribe({
            if (it.code == 0) {
                updateUser()
                it.data.forEach { comment ->
                    comment.user = userDao.findById(comment.uid).blockingFirst()
                    comment.replays?.let {replays ->
                        replays.forEach { replay ->
                            replay.user = userDao.findById(replay.uid).blockingFirst()
                        }
                    }
                }
                commentLiveData.postValue(it.data)
            } else {
                errorLiveData.postValue(it.msg)
            }
        }, {
            errorLiveData.postValue(it.message)
        })
        addCloseable { disposable1.takeIf { it.isDisposed }?.dispose() }
    }
}