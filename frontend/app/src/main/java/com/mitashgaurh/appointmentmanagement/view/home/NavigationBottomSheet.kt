package com.mitashgaurh.appointmentmanagement.view.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.BottomSheetNavigationBinding
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.ActivityUtils
import com.mitashgaurh.appointmentmanagement.util.AppUtils
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.profile.ProfileFragment
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.FragmentState
import javax.inject.Inject


class NavigationBottomSheet : BottomSheetDialogFragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mBinding by autoCleared<BottomSheetNavigationBinding>()

    private val mHomeViewModel: HomeViewModel by viewModels {
        mViewModelFactory
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        AppUtils.setWhiteNavigationBar(dialog)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<BottomSheetNavigationBinding>(
            inflater,
            R.layout.bottom_sheet_navigation,
            container,
            false
        )

        mBinding = dataBinding

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mHomeViewModel.setStudentId(PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID, ""].toString())

        initializeViews()

        subscribeToLiveData()
    }

    private fun initializeViews() {
        mBinding.layoutProfile.setOnClickListener {
            (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.VIEW_PROFILE)
            ActivityUtils.addFragmentToActivity(
                requireActivity().supportFragmentManager,
                ProfileFragment(),
                R.id.home_container,
                true,
                ProfileFragment::class.java.simpleName
            )
            dismiss()
        }
    }

    private fun subscribeToLiveData() {
        mHomeViewModel.mUserLiveData.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                mBinding.user = it
            }
        })
    }
}