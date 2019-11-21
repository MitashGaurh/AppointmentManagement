package com.mitashgaurh.appointmentmanagement.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentHistoryDao
import com.mitashgaurh.appointmentmanagement.db.dao.PaymentHistoryDao
import com.mitashgaurh.appointmentmanagement.db.dao.UserDao
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.util.PreferenceUtil
import com.mitashgaurh.appointmentmanagement.vo.AppConstants
import com.mitashgaurh.appointmentmanagement.vo.AppointmentHistoryResponse
import com.mitashgaurh.appointmentmanagement.vo.PaymentHistoryResponse
import com.mitashgaurh.appointmentmanagement.vo.Resource
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val mContext: Context,
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mAppointmentHistoryDao: AppointmentHistoryDao,
    private val mUserDao: UserDao,
    private val mPaymentHistoryDao: PaymentHistoryDao
) {

    fun callAppointmentHistoryService(studentId: String): LiveData<Resource<List<AppointmentHistory>>> {
        return object :
            NetworkBoundResource<List<AppointmentHistory>, AppointmentHistoryResponse>(mAppExecutors) {
            override fun saveCallResult(item: AppointmentHistoryResponse) {
                item.appointmentHistory.forEach {
                    mAppointmentHistoryDao.insert(it)
                }
                PreferenceUtil[mContext, AppConstants.SharedPreferenceConstants.KEY_CREATED_APPOINTMENT] =
                    false
            }

            override fun shouldFetch(data: List<AppointmentHistory>?): Boolean =
                data == null || data.isEmpty() || PreferenceUtil[mContext, AppConstants.SharedPreferenceConstants.KEY_CREATED_APPOINTMENT, false]

            override fun loadFromDb(): LiveData<List<AppointmentHistory>> {
                return mAppointmentHistoryDao.loadUpcomingAppointments(Date())
            }

            override fun createCall(): LiveData<ApiResponse<AppointmentHistoryResponse>> {
                return mService.fetchAppointmentHistory(studentId)
            }
        }.asLiveData()
    }

    fun loadUserByStudentId(studentId: String): LiveData<User> {
        return mUserDao.loadUserByStudentId(studentId)
    }

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
                return mPaymentHistoryDao.loadPendingPayments()
            }

            override fun createCall(): LiveData<ApiResponse<PaymentHistoryResponse>> {
                return mService.fetchPaymentHistory(studentId)
            }
        }.asLiveData()
    }
}