package com.example.konturtest.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.konturtest.model.local.Contact
import com.example.konturtest.model.local.EducationPeriod
import com.example.konturtest.model.local.Temperament
import java.util.*

@Entity(tableName = "contacts_table")
data class ContactDTO (@PrimaryKey val id : String = UUID.randomUUID().toString(),
                       val name : String = "",
                       val phone : String = "",
                       val height : Float = 0.0f,
                       val biography : String = "",
                       val temperament : Temperament = Temperament.MELANCHOLIC,
                       @Embedded(prefix = "ed_period_") val educationPeriod : EducationPeriod){

    fun toDomainModel() : Contact
    {
        return Contact(id, name, phone, height, biography, temperament, educationPeriod)
    }

}
