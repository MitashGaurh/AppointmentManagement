package com.mitashgaurh.appointmentmanagement.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.repository.HomeRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Event
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class HomeViewModel
@Inject constructor(private val mHomeRepository: HomeRepository) : ViewModel() {

    private var studentIdEvent = MutableLiveData<Event<String>>()

    val mLoginLiveData: LiveData<Resource<List<AppointmentHistory>>> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mHomeRepository.callAppointmentHistoryService(it.getContentIfNotHandled()!!)
            }
        }

    fun setStudentId(studentId: String) {
        studentIdEvent.value = Event(studentId)
    }
}