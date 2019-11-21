package com.mitashgaurh.appointmentmanagement.view.appointmentHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory
import com.mitashgaurh.appointmentmanagement.repository.PaymentHistoryRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class PaymentHistoryViewModel
@Inject constructor(private val mPaymentHistoryRepository: PaymentHistoryRepository) : ViewModel() {

    private var studentIdEvent = MutableLiveData<String>()

    val mPaymentHistoryLiveData: LiveData<Resource<List<PaymentHistory>>> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mPaymentHistoryRepository.callPaymentHistoryService(it)
            }
        }

    fun setStudentId(studentId: String) {
        studentIdEvent.value = studentId
    }
}