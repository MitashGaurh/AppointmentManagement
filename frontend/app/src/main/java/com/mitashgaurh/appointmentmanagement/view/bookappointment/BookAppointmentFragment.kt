package com.mitashgaurh.appointmentmanagement.view.bookappointment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.FragmentBookAppointmentBinding
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentReason
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.common.BackHandledFragment
import com.mitashgaurh.appointmentmanagement.view.home.HomeActivity
import com.mitashgaurh.appointmentmanagement.vo.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class BookAppointmentFragment : BackHandledFragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mBookAppointmentViewModel: BookAppointmentViewModel by viewModels {
        mViewModelFactory
    }

    private var mBinding by autoCleared<FragmentBookAppointmentBinding>()

    private var mTimeSlotsAdapter by autoCleared<ArrayAdapter<TimeSlot>>()

    private var mAppointmentReasonsAdapter by autoCleared<ArrayAdapter<AppointmentReason>>()

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

        mBinding.toolbar.navigationIcon =
            ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back)

        mBinding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        initializeViews()

        observeViewEvents()

        subscribeToLiveData()
    }

    private fun initializeViews() {

        initializeTimeSlotsSpinner()

        initializeAppointmentReasonsSpinner()
    }

    private fun initializeTimeSlotsSpinner() {
        val timeSlotsAdapter = ArrayAdapter<TimeSlot>(
            context!!, R.layout.support_simple_spinner_dropdown_item,
            arrayListOf()
        ) //selected item will look like a spinner set from XML

        timeSlotsAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        mTimeSlotsAdapter = timeSlotsAdapter

        mBinding.spinnerTimeSlots.adapter = timeSlotsAdapter
    }

    private fun initializeAppointmentReasonsSpinner() {
        val appointmentReasonsAdapter = ArrayAdapter<AppointmentReason>(
            context!!, R.layout.support_simple_spinner_dropdown_item,
            arrayListOf()
        ) //selected item will look like a spinner set from XML

        appointmentReasonsAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        mAppointmentReasonsAdapter = appointmentReasonsAdapter

        mBinding.spinnerAppointmentReason.adapter = appointmentReasonsAdapter
    }

    private fun observeViewEvents() {
        mBinding.toggleButtonGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            val materialButton = mBinding.toggleButtonGroup.findViewById<MaterialButton>(checkedId)
            if (isChecked) selectToggleButton(materialButton)
            else deselectToggleButton(materialButton)
        }
    }

    private fun subscribeToLiveData() {
        mBookAppointmentViewModel.mAppointmentTypesLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {
                it.data.forEach { appointmentType ->
                    val materialButton =
                        MaterialButton(context!!, null, R.attr.materialButtonOutlinedStyle)
                    materialButton.text = appointmentType.typeName

                    deselectToggleButton(materialButton)
                    materialButton.id = appointmentType.typeId.toInt()

                    materialButton.setStrokeColorResource(R.color.colorAccent)
                    mBinding.toggleButtonGroup.addView(materialButton)
                }
            }
        })

        mBookAppointmentViewModel.mAppointmentReasonsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {
                mAppointmentReasonsAdapter.addAll(it.data)
            } else {
                mAppointmentReasonsAdapter.clear()
            }
        })

        mBookAppointmentViewModel.mTimeSlotsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data && it.data.isNotEmpty()) {
                mTimeSlotsAdapter.addAll(it.data)
            } else {
                mTimeSlotsAdapter.clear()
            }
        })

        mBookAppointmentViewModel.mBookAppointmentEvent.observe(viewLifecycleOwner, EventObserver {
            mBookAppointmentViewModel.setCreateAppointmentRequest(
                CreateAppointmentRequest(
                    PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID, ""].toString(),
                    (mBinding.spinnerTimeSlots.selectedItem as TimeSlot).slotId.toInt(),
                    mBinding.toggleButtonGroup.checkedButtonId,
                    (mBinding.spinnerAppointmentReason.selectedItem as AppointmentReason).reasonId.toInt(),
                    (mBinding.spinnerTimeSlots.selectedItem as TimeSlot).appointmentDate
                )
            )
        })

        mBookAppointmentViewModel.mCreateAppointmentLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data) {
                Toast.makeText(
                    context,
                    getString(R.string.txt_appointment_creation),
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().onBackPressed()
            } else if (it.status == Status.ERROR) {
                Toast.makeText(
                    context,
                    getString(R.string.err_appointment_creation),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun submitAppointment() {
        if (mBinding.toggleButtonGroup.checkedButtonId != -1) {
            mBookAppointmentViewModel.bookAppointment()
        } else {
            Toast.makeText(context, getString(R.string.err_appointment_type), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onBackPressed(): Boolean {
        (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.HOME)
        return false
    }

    private fun selectToggleButton(materialButton: MaterialButton) {
        materialButton.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        materialButton.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
    }

    private fun deselectToggleButton(materialButton: MaterialButton) {
        materialButton.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorWhite))
        materialButton.setTextColor(ContextCompat.getColor(context!!, R.color.colorAccent))
    }
}
