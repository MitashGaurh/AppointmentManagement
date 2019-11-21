package com.mitashgaurh.appointmentmanagement.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.repository.HomeRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Event
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class HomeViewModel
@Inject constructor(private val mHomeRepository: HomeRepository) : ViewModel() {

    private var studentIdEvent = MutableLiveData<String>()

    val mAppointmentHistoryLiveData: LiveData<Resource<List<AppointmentHistory>>> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mHomeRepository.callAppointmentHistoryService(it)
            }
        }

    val mUserLiveData: LiveData<User> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mHomeRepository.loadUserByStudentId(it)
            }
        }

    val mPaymentHistoryLiveData: LiveData<Resource<List<PaymentHistory>>> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mHomeRepository.callPaymentHistoryService(it)
            }
        }

    fun setStudentId(studentId: String) {
        studentIdEvent.value = studentId
    }
}