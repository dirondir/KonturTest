package com.example.konturtest.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ContactMinimal(val id : String = UUID.randomUUID().toString(),
                          val name : String = "",
                          val phone : String = "",
                          val height : Float = 0.0f) : Parcelable
