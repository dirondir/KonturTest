package com.example.konturtest.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Contact (val id : String = UUID.randomUUID().toString(),
                    val name : String = "",
                    val phone : String = "",
                    val height : Float = 0.0f,
                    val biography : String = "",
                    val temperament : Temperament = Temperament.MELANCHOLIC,
                    val educationPeriod : EducationPeriod
) : Parcelable

