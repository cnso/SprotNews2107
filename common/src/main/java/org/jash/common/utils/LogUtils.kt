package org.jash.common.utils

import android.util.Log
import org.jash.common.BuildConfig

val Any.TAG:String
    get() = this.javaClass.simpleName

fun Any.logging(msg: Any?) {
    if(BuildConfig.DEBUG) {
        Log.d(TAG, msg.toString())
    }
}