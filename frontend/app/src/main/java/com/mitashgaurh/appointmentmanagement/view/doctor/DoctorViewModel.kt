package com.mitashgaurh.appointmentmanagement.view.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.Doctor
import com.mitashgaurh.appointmentmanagement.repository.DoctorRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class DoctorViewModel
@Inject constructor(private val mDoctorRepository: DoctorRepository) :
    ViewModel() {

    private var trueLiveData = MutableLiveData<Boolean>()

    init {
        trueLiveData.value = true
    }

    val mAvailableDoctorsLiveData: LiveData<Resource<List<Doctor>>> =
        Transformations.switchMap(trueLiveData) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mDoctorRepository.callAvailableDoctorsService()
            }
        }
}