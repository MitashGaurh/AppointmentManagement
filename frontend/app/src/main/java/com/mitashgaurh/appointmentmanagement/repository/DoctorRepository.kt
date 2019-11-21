package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.DoctorDao
import com.mitashgaurh.appointmentmanagement.db.entity.Doctor
import com.mitashgaurh.appointmentmanagement.vo.DoctorResponse
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoctorRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mDoctorDao: DoctorDao
) {

    fun callAvailableDoctorsService(): LiveData<Resource<List<Doctor>>> {
        return object :
            NetworkBoundResource<List<Doctor>, DoctorResponse>(mAppExecutors) {
            override fun saveCallResult(item: DoctorResponse) {
                item.availableDoctors.forEach {
                    mDoctorDao.insert(it)
                }
            }

            override fun shouldFetch(data: List<Doctor>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<Doctor>> {
                return mDoctorDao.loadDoctors()
            }

            override fun createCall(): LiveData<ApiResponse<DoctorResponse>> {
                return mService.fetchAvailableDoctors()
            }

        }.asLiveData()
    }
}