package com.mitashgaurh.appointmentmanagement.view.appointmentHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.FragmentAppointmentHistoryBinding
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.common.BackHandledFragment
import com.mitashgaurh.appointmentmanagement.view.home.AppointmentHistoryAdapter
import com.mitashgaurh.appointmentmanagement.view.home.HomeActivity
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.FragmentState
import com.mitashgaurh.appointmentmanagement.vo.Status
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AppointmentHistoryFragment : BackHandledFragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mAppExecutors: AppExecutors

    private val mAppointmentHistoryViewModel: AppointmentHistoryViewModel by viewModels {
        mViewModelFactory
    }

    private var mBinding by autoCleared<FragmentAppointmentHistoryBinding>()

    private var mAdapter by autoCleared<AppointmentHistoryAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentAppointmentHistoryBinding>(
            inflater,
            R.layout.fragment_appointment_history,
            container,
            false
        )

        mBinding = dataBinding

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppointmentHistoryViewModel.setStudentId(PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID, ""].toString())

        initRecyclerView()

        subscribeToLiveData()
    }

    private fun initRecyclerView() {
        val adapter = AppointmentHistoryAdapter(mAppExecutors)

        mAdapter = adapter

        mBinding.rvAppointmentHistory.adapter = adapter
        mBinding.rvAppointmentHistory.layoutManager = LinearLayoutManager(context)
    }

    private fun subscribeToLiveData() {
        mAppointmentHistoryViewModel.mAppointmentHistoryLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {
                    mBinding.emptyList = false
                    mAdapter.submitList(it.data)
                } else {
                    mBinding.emptyList = true
                    mAdapter.submitList(emptyList())
                }
            })
    }

    override fun onBackPressed(): Boolean {
        (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.HOME)
        return false
    }

}