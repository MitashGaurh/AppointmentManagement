package com.mitashgaurh.appointmentmanagement.view.bookappointment


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
import com.mitashgaurh.appointmentmanagement.databinding.FragmentBookAppointmentBinding
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.vo.Status
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class BookAppointmentFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mBookAppointmentViewModel: BookAppointmentViewModel by viewModels {
        mViewModelFactory
    }

    private var mBinding by autoCleared<FragmentBookAppointmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentBookAppointmentBinding>(
            inflater,
            R.layout.fragment_book_appointment,
            container,
            false
        )

        mBinding = dataBinding

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        mBookAppointmentViewModel.mAppointmentTypesLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {

            } else {

            }
        })

        mBookAppointmentViewModel.mTimeSlotsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {

            } else {

            }
        })
    }


}
