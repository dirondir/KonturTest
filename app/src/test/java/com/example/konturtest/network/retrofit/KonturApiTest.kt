package com.example.konturtest.network.retrofit

import android.os.Build
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class KonturApiTest{

    lateinit var apiService : KonturApiService

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        apiService = KonturApi.retrofitService

    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun testNtCall_source1()
    {
        runBlocking {
            val result = apiService.getFromSource1().mergeWith(apiService.getFromSource1()).mergeWith(apiService.getFromSource3()).doOnError {
                it.printStackTrace()
                true
            }.subscribe {
                Log.e("TEST", it.size.toString())
                MatcherAssert.assertThat(it, CoreMatchers.isA(List::class.java))
            }
        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun testNtCall_source2()
    {
        runBlocking {
            apiService.getFromSource2().subscribe {
                MatcherAssert.assertThat(it, CoreMatchers.isA(List::class.java))
            }

        }
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun testNtCall_source3()
    {
        runBlocking {
            val result = apiService.getFromSource3().subscribe {
                MatcherAssert.assertThat(it, CoreMatchers.isA(List::class.java))
            }
        }
    }



    @After
    fun tearDown() {

    }

}