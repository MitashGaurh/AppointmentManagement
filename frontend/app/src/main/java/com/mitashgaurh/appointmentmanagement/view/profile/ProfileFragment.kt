package com.mitashgaurh.appointmentmanagement.view.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.FragmentProfileBinding
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.common.BackHandledFragment
import com.mitashgaurh.appointmentmanagement.view.home.HomeActivity
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.FragmentState
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BackHandledFragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mProfileViewModel: ProfileViewModel by viewModels {
        mViewModelFactory
    }

    private var mBinding by autoCleared<FragmentProfileBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )

        mBinding = dataBinding

        return dataBinding.root
    }

    override fun onBackPressed(): Boolean {
        if (mBinding.editMode!!) {
            mBinding.editMode = mBinding.editMode?.not()
            (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.VIEW_PROFILE)
            return true
        }
        (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.HOME)
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProfileViewModel.setStudentId(PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID, ""].toString())

        mBinding.toolbar.navigationIcon =
            ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back)

        mBinding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        toggleEditMode(false)

        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        mProfileViewModel.mUserLiveData.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                mBinding.user = it
            }
        })
    }

    fun toggleEditMode(value: Boolean) {
        mBinding.editMode = value
    }

    fun updateUserProfile() {

    }
}
