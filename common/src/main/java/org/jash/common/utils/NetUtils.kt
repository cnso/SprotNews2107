package org.jash.common.utils

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jash.common.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
var token:String? = null

private val client by lazy {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
    builder.addInterceptor {
        it.proceed(if (token == null) {
            it.request()
        } else {
            it.request().newBuilder().addHeader("sn-token", token).build()
        })
    }
    builder.build()
}
private val gson by lazy {
    GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
}
val retrofit by lazy {
    Retrofit.Builder()
        .client(client)
        .baseUrl("http://10.161.9.80:7014/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}