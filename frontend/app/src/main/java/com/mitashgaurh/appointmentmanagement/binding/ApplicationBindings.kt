package com.mitashgaurh.appointmentmanagement.binding

import android.view.View
import androidx.databinding.BindingAdapter

object ApplicationBindings {

    @JvmStatic
    @BindingAdapter("app:visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}