package com.example.konturtest.app

import android.app.Application
import com.example.konturtest.network.retrofit.KonturApi
import com.example.konturtest.repo.ContactsDataSource
import com.example.konturtest.repo.ContactsRepository
import com.example.konturtest.room.ContactsDatabase
import com.example.konturtest.ui.main.MainFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KonturTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {

            single { KonturApi.retrofitService}
            single { ContactsDatabase.getInstance(this@KonturTestApp).contactsDao }
            single { ContactsRepository(get(), get()) as ContactsDataSource }

            viewModel { MainFragmentViewModel(this@KonturTestApp, get()) }

        }

        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@KonturTestApp)
            modules(appModule)
        }
    }
}