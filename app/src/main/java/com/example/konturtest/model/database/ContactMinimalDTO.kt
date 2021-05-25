package com.example.konturtest.model.database

import java.util.*

data class ContactMinimalDTO(val id : String = UUID.randomUUID().toString(),
                             val name : String = "",
                             val phone : String = "",
                             val height : Float = 0.0f)
