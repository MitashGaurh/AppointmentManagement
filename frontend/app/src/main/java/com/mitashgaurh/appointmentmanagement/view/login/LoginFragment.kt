package com.mitashgaurh.appointmentmanagement.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.FragmentLoginBinding
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.AppUtils
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.home.HomeActivity
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.Status
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mLoginViewModel: LoginViewModel by viewModels {
        mViewModelFactory
    }

    private var mBinding by autoCleared<FragmentLoginBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        mBinding = dataBinding

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.viewModel = mLoginViewModel

        requestFocusForView()

        subscribeToLiveData()
    }

    private fun requestFocusForView() {
        mBinding.showProgress = false
        mBinding.textInputLayoutId.requestFocus()
        activity?.let { AppUtils.showKeyboard(it) }
    }

    private fun subscribeToLiveData() {
        mLoginViewModel.mLoginLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.LOADING) {
                mBinding.showProgress = true
                mBinding.btnLogin.background.alpha = 180
                activity?.let { it1 -> AppUtils.hideKeyboard(it1, mBinding.root) }
            } else if (it.status == Status.SUCCESS && null != it.data) {
                mBinding.showProgress = false
                mBinding.btnLogin.background.alpha = 255
                PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID] = it.data.studentId
                startActivity(Intent(activity, HomeActivity::class.java))
                activity?.finish()
            } else if (it.status == Status.ERROR) {
                mBinding.showProgress = false
                mBinding.btnLogin.background.alpha = 255
            }
        })
    }
}
