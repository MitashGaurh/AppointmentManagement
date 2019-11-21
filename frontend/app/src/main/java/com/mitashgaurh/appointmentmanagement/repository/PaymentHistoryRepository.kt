package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.PaymentHistoryDao
import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory
import com.mitashgaurh.appointmentmanagement.vo.PaymentHistoryResponse
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentHistoryRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mPaymentHistoryDao: PaymentHistoryDao
) {

    fun callPaymentHistoryService(studentId: String): LiveData<Resource<List<PaymentHistory>>> {
        return object :
            NetworkBoundResource<List<PaymentHistory>, PaymentHistoryResponse>(mAppExecutors) {
            override fun saveCallResult(item: PaymentHistoryResponse) {
                item.paymentHistory.forEach {
                    mPaymentHistoryDao.insert(it)
                }
            }

            override fun shouldFetch(data: List<PaymentHistory>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<PaymentHistory>> {
                return mPaymentHistoryDao.loadPaymentHistory()
            }

            override fun createCall(): LiveData<ApiResponse<PaymentHistoryResponse>> {
                return mService.fetchPaymentHistory(studentId)
            }
        }.asLiveData()
    }
}