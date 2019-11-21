package com.mitashgaurh.appointmentmanagement.view.bookappointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentReason
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot
import com.mitashgaurh.appointmentmanagement.repository.BookAppointmentRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.CreateAppointmentRequest
import com.mitashgaurh.appointmentmanagement.vo.Event
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

    val mAppointmentReasonsLiveData: LiveData<Resource<List<AppointmentReason>>> =
        Transformations.switchMap(trueLiveData) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mBookAppointmentRepository.callAppointmentReasonService()
            }
        }

    val mBookAppointmentEvent = MutableLiveData<Event<Boolean>>()

    fun bookAppointment() {
        mBookAppointmentEvent.value = Event(true)
    }

    private var createAppointmentRequest = MutableLiveData<CreateAppointmentRequest>()

    val mCreateAppointmentLiveData: LiveData<Resource<Any>> =
        Transformations.switchMap(createAppointmentRequest) {
            if (null == it) {
                AbsentLiveData.create<Resource<Any>>()
            } else {
                mBookAppointmentRepository.triggerCreateAppointmentService(it)
            }
        }

    fun setCreateAppointmentRequest(createAppointmentRequest: CreateAppointmentRequest) {
        this.createAppointmentRequest.value = createAppointmentRequest
    }
}