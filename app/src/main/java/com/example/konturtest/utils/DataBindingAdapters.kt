package com.example.konturtest.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.konturtest.R
import com.example.konturtest.model.local.Contact
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @BindingAdapter("contactHeight")
    @JvmStatic
    fun setIsFollowedText(view: TextView, height: Float = 0.0f) {
        if (height.isNaN())
            view.text = view.context.getString(R.string.there_is_no_height)
        else
            view.text = String.format(Locale.US, "%.2f", height)

    }

    @BindingAdapter("android:isVisible")
    @JvmStatic
    fun setVisible(view: View, visible: Boolean? = true) {
        if (visible == true) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

    @BindingAdapter("android:period")
    @JvmStatic
    fun setPeriod(view: TextView, contact: Contact?) {
        if (contact == null) {
            view.text = ""
            return
        }

        val formatterFrom = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        val formatterTo = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val fromDate = formatterTo.format(formatterFrom.parse(contact.educationPeriod.start)!!)
        val toDate = formatterTo.format(formatterFrom.parse(contact.educationPeriod.end)!!)

        view.text = "$fromDate - $toDate"

    }

}