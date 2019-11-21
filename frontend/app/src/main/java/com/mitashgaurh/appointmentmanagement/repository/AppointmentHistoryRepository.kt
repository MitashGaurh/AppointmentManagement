package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentHistoryDao
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.vo.AppointmentHistoryResponse
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppointmentHistoryRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mAppointmentHistoryDao: AppointmentHistoryDao
) {

    fun callAppointmentHistoryService(studentId: String): LiveData<Resource<List<AppointmentHistory>>> {
        return object :
            NetworkBoundResource<List<AppointmentHistory>, AppointmentHistoryResponse>(mAppExecutors) {
            override fun saveCallResult(item: AppointmentHistoryResponse) {
                item.appointmentHistory.forEach {
                    mAppointmentHistoryDao.insert(it)
                }
            }

            override fun shouldFetch(data: List<AppointmentHistory>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<AppointmentHistory>> {
                return mAppointmentHistoryDao.loadAppointmentHistory()
            }

            override fun createCall(): LiveData<ApiResponse<AppointmentHistoryResponse>> {
                return mService.fetchAppointmentHistory(studentId)
            }
        }.asLiveData()
    }
}