package com.example.konturtest.model.network

import com.example.konturtest.model.local.EducationPeriod
import com.example.konturtest.model.local.Temperament
import java.util.*

data class ContactNetwork (val id : String = UUID.randomUUID().toString(),
                           val name : String = "",
                           val phone : String = "",
                           val height : Float = 0.0f,
                           val biography : String = "",
                           val temperament : Temperament = Temperament.MELANCHOLIC,
                           val educationPeriod : EducationPeriod
)

