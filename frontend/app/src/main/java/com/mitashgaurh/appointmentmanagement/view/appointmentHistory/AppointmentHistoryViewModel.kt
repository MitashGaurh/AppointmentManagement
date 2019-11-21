package com.mitashgaurh.appointmentmanagement.view.appointmentHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.repository.AppointmentHistoryRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class AppointmentHistoryViewModel
@Inject constructor(private val mAppointmentHistoryRepository: AppointmentHistoryRepository) : ViewModel() {

    private var studentIdEvent = MutableLiveData<String>()

    val mAppointmentHistoryLiveData: LiveData<Resource<List<AppointmentHistory>>> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mAppointmentHistoryRepository.callAppointmentHistoryService(it)
            }
        }

    fun setStudentId(studentId: String) {
        studentIdEvent.value = studentId
    }
}