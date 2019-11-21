package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentReasonDao
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentTypeDao
import com.mitashgaurh.appointmentmanagement.db.dao.TimeSlotDao
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentReason
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot
import com.mitashgaurh.appointmentmanagement.vo.*
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookAppointmentRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mTimeSlotDao: TimeSlotDao,
    private val mAppointmentTypeDao: AppointmentTypeDao,
    private val mAppointmentReasonDao: AppointmentReasonDao
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
                    mAppointmentTypeDao.insert(it)
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

    fun callAppointmentReasonService(): LiveData<Resource<List<AppointmentReason>>> {
        return object :
            NetworkBoundResource<List<AppointmentReason>, AppointmentReasonResponse>(mAppExecutors) {
            override fun saveCallResult(item: AppointmentReasonResponse) {
                item.appointmentReasons.forEach {
                    mAppointmentReasonDao.insert(it)
                }
            }

            override fun shouldFetch(data: List<AppointmentReason>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<AppointmentReason>> {
                return mAppointmentReasonDao.loadAppointmentReasons()
            }

            override fun createCall(): LiveData<ApiResponse<AppointmentReasonResponse>> {
                return mService.fetchAppointmentReasons()
            }

        }.asLiveData()
    }

    fun triggerCreateAppointmentService(createAppointmentRequest: CreateAppointmentRequest): MutableLiveData<Resource<Any>> {
        val resultLiveData: MutableLiveData<Resource<Any>> = MutableLiveData()
        resultLiveData.postValue(Resource(Status.LOADING, null, null))

        mService.createNewAppointment(createAppointmentRequest)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(
                    call: @NotNull Call<Any?>?,
                    response: @NotNull Response<Any?>?
                ) {
                    if (response?.isSuccessful!!) resultLiveData.postValue(
                        Resource(
                            Status.SUCCESS,
                            response.body(),
                            "Inside onResponse"
                        )
                    )
                    else resultLiveData.postValue(
                        Resource(
                            Status.ERROR,
                            response.body(),
                            "Login Failure."
                        )
                    )
                }

                override fun onFailure(
                    call: @NotNull Call<Any?>?,
                    t: @NotNull Throwable?
                ) {
                    resultLiveData.postValue(Resource(Status.ERROR, null, "Login Failure."))
                }
            })

        return resultLiveData
    }
}