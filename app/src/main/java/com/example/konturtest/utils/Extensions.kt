package com.example.konturtest.utils

import android.view.View
import android.view.View.*
import com.example.konturtest.model.database.ContactDTO
import com.example.konturtest.model.database.ContactMinimalDTO
import com.example.konturtest.model.local.Contact
import com.example.konturtest.model.local.ContactMinimal
import com.example.konturtest.model.network.ContactNetwork

fun List<ContactNetwork>.asDTOModel(): List<ContactDTO> {
    return map {
        ContactDTO(
            it.id,
            it.name,
            it.phone,
            it.height,
            it.biography,
            it.temperament,
            it.educationPeriod
        )
    }
}

fun List<ContactDTO>.asDomainModel(): List<Contact> {
    return map {
        Contact(
            it.id,
            it.name,
            it.phone,
            it.height,
            it.biography,
            it.temperament,
            it.educationPeriod
        )
    }
}

fun List<ContactMinimalDTO>.asDomainMinimalModel(): List<ContactMinimal> {
    return map {
        ContactMinimal(
            it.id,
            it.name,
            it.phone,
            it.height
        )
    }
}

fun View.visible()
{
    visibility = VISIBLE
}

fun View.invisible()
{
    visibility = INVISIBLE
}

fun View.gone()
{
    visibility = GONE
}