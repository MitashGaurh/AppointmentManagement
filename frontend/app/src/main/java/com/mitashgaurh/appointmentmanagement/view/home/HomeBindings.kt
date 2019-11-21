package com.mitashgaurh.appointmentmanagement.view.home

import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.vo.FragmentState

object HomeBindings {

    @JvmStatic
    @BindingAdapter("app:fabAlignment")
    fun fabAlignment(bottomAppBar: BottomAppBar, fragmentState: FragmentState) {
        bottomAppBar.fabAlignmentMode =
            if (fragmentState == FragmentState.HOME) BottomAppBar.FAB_ALIGNMENT_MODE_CENTER else BottomAppBar.FAB_ALIGNMENT_MODE_END

        bottomAppBar.navigationIcon =
            if (fragmentState == FragmentState.HOME) ContextCompat.getDrawable(
                bottomAppBar.context,
                R.drawable.ic_menu
            ) else null
    }

    @JvmStatic
    @BindingAdapter("app:fabIcon")
    fun fabAlignment(fab: FloatingActionButton, fragmentState: FragmentState) {
        fab.setImageDrawable(
            when (fragmentState) {
                FragmentState.HOME -> ContextCompat.getDrawable(
                    fab.context,
                    R.drawable.ic_add_white
                )
                FragmentState.BOOK_APPOINTMENT -> ContextCompat.getDrawable(
                    fab.context,
                    R.drawable.ic_done_white
                )
                FragmentState.EDIT_PROFILE -> ContextCompat.getDrawable(
                    fab.context,
                    R.drawable.ic_edit_white
                )
            }
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