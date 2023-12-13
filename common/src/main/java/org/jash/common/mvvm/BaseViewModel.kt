package org.jash.common.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val errorLiveData by lazy { MutableLiveData<String>() }
}