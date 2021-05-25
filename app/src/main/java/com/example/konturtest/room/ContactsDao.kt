package com.example.konturtest.room

import androidx.room.*
import com.example.konturtest.model.database.ContactDTO
import com.example.konturtest.model.database.ContactMinimalDTO
import io.reactivex.rxjava3.core.Completable

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContacts(contacts : List<ContactDTO>)

    @Query("Select id, name, phone, height from contacts_table")
    fun getAllContactsMinimal() : List<ContactMinimalDTO>

    @Query("Select id, name, phone, height from contacts_table where name LIKE :searchText or phone LIKE :searchText")
    fun searchContacts(searchText : String) : List<ContactMinimalDTO>?

    @Query("Select * from contacts_table where id = :id")
    fun getContactWithId(id : String) : ContactDTO?

    @Query("Delete from contacts_table")
    fun clearAllContacts()


}