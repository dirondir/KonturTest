package com.example.konturtest.repo

import com.example.konturtest.model.local.Contact
import com.example.konturtest.model.local.ContactMinimal
import com.example.konturtest.network.retrofit.KonturApiService
import com.example.konturtest.room.ContactsDao
import com.example.konturtest.utils.asDTOModel
import com.example.konturtest.utils.asDomainMinimalModel
import io.reactivex.rxjava3.core.Completable

class ContactsRepository(private val mContactsDao : ContactsDao,
                         private val mKonturApiService: KonturApiService) : ContactsDataSource {

    override fun loadContacts(): Completable {

                return mKonturApiService.getFromSource1()
                    .mergeWith(mKonturApiService.getFromSource2())
                    .mergeWith(mKonturApiService.getFromSource3())
                        .flatMapCompletable { contacts -> Completable.fromAction { mContactsDao.saveContacts(contacts.asDTOModel())} }
        }

    override suspend  fun getContactsMinimal(): List<ContactMinimal> {
        return mContactsDao.getAllContactsMinimal().asDomainMinimalModel()
    }

    override suspend fun getContactWithId(id: String): Contact? {
        return mContactsDao.getContactWithId(id)?.toDomainModel()
    }

    override suspend fun searchContacts(query: String): List<ContactMinimal> {
        return mContactsDao.searchContacts(query)?.asDomainMinimalModel() ?: ArrayList()
    }
}