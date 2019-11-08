package com.mitashgaurh.appointmentmanagement.view.home

import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mitashgaurh.appointmentmanagement.R

object HomeBindings {

    @JvmStatic
    @BindingAdapter("app:fabAlignment")
    fun fabAlignment(bottomAppBar: BottomAppBar, currentIsHome: Boolean) {
        bottomAppBar.fabAlignmentMode =
            if (currentIsHome) BottomAppBar.FAB_ALIGNMENT_MODE_CENTER else BottomAppBar.FAB_ALIGNMENT_MODE_END
    }

    @JvmStatic
    @BindingAdapter("app:fabIcon")
    fun fabAlignment(fab: FloatingActionButton, currentIsHome: Boolean) {
        fab.setImageDrawable(
            if (currentIsHome) ContextCompat.getDrawable(
                fab.context,
                R.drawable.ic_add_white
            ) else ContextCompat.getDrawable(fab.context, R.drawable.ic_arrow_back)
        )
    }

    @JvmStatic
    @BindingAdapter("app:appointmentStatus")
    fun setAppointmentStatus(textView: TextView, status: String) {
        when (status) {
            "APPROVED" -> textView.setTextColor(Color.GREEN)
            "CANCELLED" -> textView.setTextColor(Color.RED)
            "PENDING" -> textView.setTextColor(Color.YELLOW)
            else -> textView.setTextColor(Color.DKGRAY)
        }
    }

}