package com.example.konturtest.room

import androidx.room.TypeConverter
import com.example.konturtest.model.local.Temperament
import java.util.*

class Converters {
    @TypeConverter
    fun fromTemperament(value: Temperament?): Int? {
        return when(value){
            Temperament.CHOLERIC->0
            Temperament.PHLEGMATIC->1
            Temperament.MELANCHOLIC->2
            Temperament.SANGUINE->3
            else->null
        }
    }

    @TypeConverter
    fun toTemperament(value: Int): Temperament? {
        return when(value){
            0->Temperament.CHOLERIC
            1->Temperament.PHLEGMATIC
            2->Temperament.MELANCHOLIC
            3->Temperament.SANGUINE
            else->null
        }
    }
}