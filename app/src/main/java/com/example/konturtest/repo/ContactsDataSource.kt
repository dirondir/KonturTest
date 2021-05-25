package com.example.konturtest.repo

import com.example.konturtest.model.local.Contact
import com.example.konturtest.model.local.ContactMinimal
import io.reactivex.rxjava3.core.Completable

interface ContactsDataSource {

    fun loadContacts() : Completable
    suspend fun getContactsMinimal() : List<ContactMinimal>
    suspend fun getContactWithId(id : String) : Contact?
    suspend fun searchContacts(query : String) : List<ContactMinimal>

}