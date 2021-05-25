package com.example.konturtest.network.retrofit

import com.example.konturtest.model.network.ContactNetwork
import com.example.konturtest.network.adapters.TemperamentAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Flowable

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/"

// custom adapters
private val moshi = Moshi.Builder()
        .add(TemperamentAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

/**
 *  Documentation for the Google Civics API Service can be found at https://developers.google.com/civic-information/docs/v2
 */

interface KonturApiService {

    @GET("generated-01.json")
    fun getFromSource1() : Flowable<List<ContactNetwork>>

    @GET("generated-02.json")
    fun getFromSource2() : Flowable<List<ContactNetwork>>

    @GET("generated-03.json")
    fun getFromSource3() : Flowable<List<ContactNetwork>>

}

object KonturApi {
    val retrofitService: KonturApiService by lazy {
        retrofit.create(KonturApiService::class.java)
    }
}