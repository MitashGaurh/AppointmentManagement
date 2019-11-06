package com.mitashgaurh.appointmentmanagement.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


/**
 * This provides methods to help Activities load their UI.
 */
object ActivityUtils {

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun addFragmentToActivity(
        fragmentManager: FragmentManager
        , fragment: Fragment, frameId: Int
        , isAddToBackStack: Boolean, tag: String?
    ) {
        val transaction = fragmentManager.beginTransaction()
        if (null != tag) {
            transaction.replace(frameId, fragment, tag)
        } else {
            transaction.replace(frameId, fragment)
        }
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun addNonUIFragmentToActivity(
        fragmentManager: FragmentManager
        , nonUIFragment: Fragment, fragmentTag: String
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(nonUIFragment, fragmentTag)
        transaction.commit()
    }

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun addFragmentToActivityWithSharedElement(
        fragmentManager: FragmentManager
        , fragment: Fragment, frameId: Int
        , sharedElement: View, transitionName: String
        , isAddToBackStack: Boolean, tag: String?
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.addSharedElement(sharedElement, transitionName)
        if (null != tag) {
            transaction.replace(frameId, fragment, tag)
        } else {
            transaction.replace(frameId, fragment)
        }
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }
}
