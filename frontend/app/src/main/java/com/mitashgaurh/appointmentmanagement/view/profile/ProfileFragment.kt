package com.mitashgaurh.appointmentmanagement.view.profile


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.FragmentProfileBinding
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.di.Injectable
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.util.autoCleared
import com.mitashgaurh.appointmentmanagement.view.common.BackHandledFragment
import com.mitashgaurh.appointmentmanagement.view.home.HomeActivity
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.EventObserver
import com.mitashgaurh.appointmentmanagement.vo.FragmentState
import com.mitashgaurh.appointmentmanagement.vo.Status
import java.text.SimpleDateFormat
import java.util.*
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
            toggleEditMode(mBinding.editMode?.not()!!)
            updateBindingUser(mBinding.user!!)
            (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.VIEW_PROFILE)
            return true
        }
        (requireActivity() as HomeActivity).toggleFabBehavior(FragmentState.HOME)
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProfileViewModel.setStudentId(PreferenceUtil[context!!, AppConstants.SharedPreferenceConstants.KEY_USER_ID, ""].toString())

        toggleEditMode(false)

        toggleToolbarNavigationIcon()

        mBinding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        toggleDateOfBirthViewEvent()

        subscribeToLiveData()
    }

    private fun toggleToolbarNavigationIcon() {
        mBinding.toolbar.navigationIcon = if (mBinding.editMode!!)
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_close_black
            ) else ContextCompat.getDrawable(context!!, R.drawable.ic_arrow_back)
    }

    private fun toggleDateOfBirthViewEvent() {
        if (mBinding.editMode!!) {
            mBinding.etDob.setOnClickListener {
                mProfileViewModel.triggerPickDateEvent(mBinding.user?.dateOfBirth!!)
            }
        } else {
            mBinding.etDob.setOnClickListener(null)
        }
    }

    private fun subscribeToLiveData() {
        mProfileViewModel.mUserLiveData.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                updateBindingUser(it)
            }
        })

        mProfileViewModel.mUpdateProfileLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS && null != it.data) {
                Toast.makeText(
                    context,
                    getString(R.string.txt_user_update),
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().onBackPressed()
            } else if (it.status == Status.ERROR) {
                Toast.makeText(
                    context,
                    getString(R.string.err_user_update),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        mProfileViewModel.mPickDateEvent.observe(this, EventObserver {
            val calendar = Calendar.getInstance()
            calendar.time = it
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { view, _year, _monthOfYear, _dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.YEAR, _year)
                    cal.set(Calendar.MONTH, _monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, _dayOfMonth)
                    mBinding.etDob.setText(SimpleDateFormat.getDateInstance().format(cal.time))
                    mBinding.etDob.tag = cal.time
                    year = _year
                    month = _monthOfYear
                    day = _dayOfMonth
                }, year, month, day
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        })
    }

    fun toggleEditMode(value: Boolean) {
        mBinding.editMode = value

        toggleToolbarNavigationIcon()

        toggleDateOfBirthViewEvent()
    }

    private fun updateBindingUser(user: User) {
        mBinding.user = user
        mBinding.etDob.tag = user.dateOfBirth
    }

    fun updateUserProfile() {
        if (mBinding.user?.studentId != mBinding.etId.text.toString()
            || mBinding.user?.firstName != mBinding.etFirstName.text.toString()
            || mBinding.user?.lastName != mBinding.etLastName.text.toString()
            || mBinding.user?.emailId != mBinding.etEmail.text.toString()
            || mBinding.user?.mobileNumber != mBinding.etMobile.text.toString()
            || mBinding.user?.address != mBinding.etAddress.text.toString()
            || SimpleDateFormat.getDateInstance().parse(
                SimpleDateFormat.getDateInstance().format(
                    mBinding.user?.dateOfBirth!!
                )
            ) != SimpleDateFormat.getDateInstance().parse(mBinding.etDob.text.toString())
        ) {
            mProfileViewModel.setUpdateProfileRequest(
                User(
                    mBinding.user?.studentId!!,
                    mBinding.etFirstName.text.toString(),
                    mBinding.etLastName.text.toString(),
                    mBinding.etEmail.text.toString(),
                    mBinding.etMobile.text.toString(),
                    mBinding.etAddress.text.toString(),
                    mBinding.etDob.tag as Date
                )
            )
        } else {
            Toast.makeText(context, "No changes made to profile", Toast.LENGTH_SHORT).show()
        }
    }
}
