package com.mitashgaurh.appointmentmanagement.view.home


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
import com.mitashgaurh.appointmentmanagement.databinding.FragmentHomeBinding
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.common.BackHandledFragment
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.Status
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BackHandledFragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mAppExecutors: AppExecutors

    private val mHomeViewModel: HomeViewModel by viewModels {
        mViewModelFactory
    }

    private var mBinding by autoCleared<FragmentHomeBinding>()

    private var mScheduledAppointmentAdapter by autoCleared<AppointmentHistoryAdapter>()

    private var mPendingPaymentAdapter by autoCleared<PaymentHistoryAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        mBinding = dataBinding

        return dataBinding.root
    }

    override fun onBackPressed() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mHomeViewModel.setStudentId(PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID, ""].toString())

        initScheduledAppointmentRecyclerView()

        initPendingPaymentsRecyclerView()

        subscribeToLiveData()
    }

    private fun initScheduledAppointmentRecyclerView() {
        val adapter = AppointmentHistoryAdapter(mAppExecutors)

        mScheduledAppointmentAdapter = adapter

        mBinding.rvScheduledAppointments.adapter = adapter
        mBinding.rvScheduledAppointments.layoutManager = LinearLayoutManager(context)

        mBinding.rvScheduledAppointments.isNestedScrollingEnabled = false
    }

    private fun initPendingPaymentsRecyclerView() {
        val adapter = PaymentHistoryAdapter(mAppExecutors)

        mPendingPaymentAdapter = adapter

        mBinding.rvPendingPayment.adapter = adapter
        mBinding.rvPendingPayment.layoutManager = LinearLayoutManager(context)

        mBinding.rvPendingPayment.isNestedScrollingEnabled = false
    }

    private fun subscribeToLiveData() {
        mHomeViewModel.mAppointmentHistoryLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {
                mBinding.emptyScheduledList = false
                mScheduledAppointmentAdapter.submitList(it.data)
            } else {
                mBinding.emptyScheduledList = true
                mScheduledAppointmentAdapter.submitList(emptyList())
            }
        })

        mHomeViewModel.mPaymentHistoryLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {
                mBinding.emptyPendingPaymentList = false
                mPendingPaymentAdapter.submitList(it.data)
            } else {
                mBinding.emptyPendingPaymentList = true
                mPendingPaymentAdapter.submitList(emptyList())
            }
        })
    }
}
