package com.example.konturtest.network.adapters

import com.example.konturtest.model.local.Temperament
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class TemperamentAdapter {
    @FromJson
    fun temperamentFromJson (temperament: String): Temperament {
        return when (temperament){
            "melancholic" -> Temperament.MELANCHOLIC
            "choleric" -> Temperament.CHOLERIC
            "phlegmatic" -> Temperament.PHLEGMATIC
            "sanguine" -> Temperament.SANGUINE
            else -> Temperament.MELANCHOLIC
        }
    }

    @ToJson
    fun temperamentToJson (temperament: Temperament): String {
        return temperament.name
    }
}