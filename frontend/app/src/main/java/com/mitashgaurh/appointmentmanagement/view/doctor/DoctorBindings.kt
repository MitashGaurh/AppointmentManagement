package com.mitashgaurh.appointmentmanagement.view.doctor

import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mitashgaurh.appointmentmanagement.R


object DoctorBindings {

    @JvmStatic
    @BindingAdapter("app:doctorLayout")
    fun doctorTimings(linearLayout: LinearLayout, officeHours: String) {
        if (linearLayout.childCount > 0) linearLayout.removeAllViews()

        val hours = officeHours.split(",")

        val inflater = LayoutInflater.from(linearLayout.context)

        hours.forEach {
            val textView =
                inflater.inflate(
                    R.layout.layout_doctor_office_hour,
                    linearLayout,
                    false
                ) as TextView
            textView.text = it
            linearLayout.addView(textView)
        }
    }
}