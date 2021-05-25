package com.example.konturtest.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.konturtest.model.local.Contact
import com.example.konturtest.model.local.ContactMinimal
import com.example.konturtest.prefs.UtilsPrefs
import com.example.konturtest.repo.ContactsDataSource
import com.example.konturtest.utils.SingleLiveEvent
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel(application: Application, private val mDataSource: ContactsDataSource) :
    AndroidViewModel(application) {

    var mContacts = MutableLiveData<List<ContactMinimal>>()
    var mFoundContacts = MutableLiveData<List<ContactMinimal>>()
    val mErrorText = SingleLiveEvent<String>()

    val mShowProgress = MutableLiveData(false)
    val mSelectedItem = SingleLiveEvent<Contact?>()

    init {
        loadContacts()
    }

    fun loadContacts() {
        mShowProgress.postValue(true)
        if (UtilsPrefs.itsLoadTime(getApplication())) {
            mDataSource.loadContacts().observeOn(Schedulers.io()).
                subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                override fun onComplete() {
                    UtilsPrefs.saveDate(getApplication())
                    loadFromDB()
                }

                override fun onError(e: Throwable) {
                    mShowProgress.postValue(false)
                    mErrorText.postValue("Что-то пошло не так \n${e.message}")
                }

                override fun onSubscribe(d: Disposable) {


                }
            })
        } else
            loadFromDB()
    }

    fun loadFromDB() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mContacts.postValue(mDataSource.getContactsMinimal())
                mShowProgress.postValue(false)
            }
        }
    }

    fun getContactInfo(contact : ContactMinimal) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contactInfo = mDataSource.getContactWithId(contact.id)
                if (contactInfo != null)
                    mSelectedItem.postValue(contactInfo!!)

            }
        }
    }

    fun search(query : String?)
    {
        if (query == null) return

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mFoundContacts.postValue(mDataSource.searchContacts(query!!))
            }
        }

    }

    fun finishSearch()
    {
        mFoundContacts.postValue(emptyList())
    }
}