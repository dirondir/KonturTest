package com.example.konturtest.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EducationPeriod (val start : String,
                            val end : String) : Parcelable