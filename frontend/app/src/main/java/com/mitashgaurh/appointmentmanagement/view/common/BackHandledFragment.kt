package com.mitashgaurh.appointmentmanagement.view.common

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BackHandledFragment : Fragment() {

    companion object {
        private const val TAG = "BackHandledFragment"
    }

    private var mBackHandlerInterface: BackHandlerInterface? = null

    abstract fun onBackPressed(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is BackHandlerInterface) {
            throw ClassCastException("Hosting activity must implement BackHandlerInterface")
        } else {
            mBackHandlerInterface = activity as BackHandlerInterface
        }
    }

    override fun onStart() {
        super.onStart()

        // Mark this fragment as the selected Fragment.
        mBackHandlerInterface?.setSelectedFragment(this)
    }

    interface BackHandlerInterface {

        fun setSelectedFragment(backHandledFragment: BackHandledFragment?)
    }
}