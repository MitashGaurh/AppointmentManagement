package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentTypeDao
import com.mitashgaurh.appointmentmanagement.db.dao.TimeSlotDao
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot
import com.mitashgaurh.appointmentmanagement.vo.AppointmentTypeResponse
import com.mitashgaurh.appointmentmanagement.vo.Resource
import com.mitashgaurh.appointmentmanagement.vo.TimeSlotsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookAppointmentRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mTimeSlotDao: TimeSlotDao,
    private val mAppointmentTypeDao: AppointmentTypeDao
) {

    fun callTimeSlotsService(): LiveData<Resource<List<TimeSlot>>> {
        return object :
            NetworkBoundResource<List<TimeSlot>, TimeSlotsResponse>(mAppExecutors) {
            override fun saveCallResult(item: TimeSlotsResponse) {
                item.availableTimeSlots.forEach {
                    mTimeSlotDao.insert(it)
                }
            }

            override fun shouldFetch(data: List<TimeSlot>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<TimeSlot>> {
                return mTimeSlotDao.loadTimeSlots()
            }

            override fun createCall(): LiveData<ApiResponse<TimeSlotsResponse>> {
                return mService.fetchAvailableTimeSlots()
            }

        }.asLiveData()
    }

    fun callAppointmentTypeService(): LiveData<Resource<List<AppointmentType>>> {
        return object :
            NetworkBoundResource<List<AppointmentType>, AppointmentTypeResponse>(mAppExecutors) {
            override fun saveCallResult(item: AppointmentTypeResponse) {
                item.appointmentTypes.forEach {
                    mAppointmentTypeDao.insert(AppointmentType(it))
                }
            }

            override fun shouldFetch(data: List<AppointmentType>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<AppointmentType>> {
                return mAppointmentTypeDao.loadAppointmentTypes()
            }

            override fun createCall(): LiveData<ApiResponse<AppointmentTypeResponse>> {
                return mService.fetchAppointmentTypes()
            }

        }.asLiveData()
    }
}