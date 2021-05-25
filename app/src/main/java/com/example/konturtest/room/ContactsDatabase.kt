package com.example.konturtest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.konturtest.model.database.ContactDTO

@Database(entities = [ContactDTO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ContactsDatabase: RoomDatabase() {

    abstract val contactsDao: ContactsDao

    companion object {

        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ContactsDatabase::class.java,
                            "contacts_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}