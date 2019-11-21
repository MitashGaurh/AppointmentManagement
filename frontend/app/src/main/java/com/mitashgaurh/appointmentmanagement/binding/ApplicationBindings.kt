package com.mitashgaurh.appointmentmanagement.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object ApplicationBindings {

    @JvmStatic
    @BindingAdapter("app:visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:dateToText")
    fun formatDate(view: TextView, date: Date?) {
        if (null != date) {
            val formatter = SimpleDateFormat.getDateInstance()
            view.text = formatter.format(date)
        }
    }
}