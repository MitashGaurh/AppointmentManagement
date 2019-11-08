package com.mitashgaurh.appointmentmanagement.view.bookappointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot
import com.mitashgaurh.appointmentmanagement.repository.BookAppointmentRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class BookAppointmentViewModel
@Inject constructor(private val mBookAppointmentRepository: BookAppointmentRepository) :
    ViewModel() {

    private var trueLiveData = MutableLiveData<Boolean>()

    init {
        trueLiveData.value = true
    }

    val mTimeSlotsLiveData: LiveData<Resource<List<TimeSlot>>> =
        Transformations.switchMap(trueLiveData) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mBookAppointmentRepository.callTimeSlotsService()
            }
        }

    val mAppointmentTypesLiveData: LiveData<Resource<List<AppointmentType>>> =
        Transformations.switchMap(trueLiveData) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mBookAppointmentRepository.callAppointmentTypeService()
            }
        }
}