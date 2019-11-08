package com.mitashgaurh.appointmentmanagement.api

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.vo.AppointmentHistoryResponse
import com.mitashgaurh.appointmentmanagement.vo.AppointmentTypeResponse
import com.mitashgaurh.appointmentmanagement.vo.LoginResponse
import com.mitashgaurh.appointmentmanagement.vo.TimeSlotsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AppointmentManagementService {

    @FormUrlEncoded
    @POST("/login")
    fun performLogin(
        @Field("studentId") studentId: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<LoginResponse>>

    @FormUrlEncoded
    @POST("/getAppointmentHistory")
    fun fetchAppointmentHistory(@Field(value = "studentId") studentId: String): LiveData<ApiResponse<AppointmentHistoryResponse>>

    @GET("/getAvailableTimeSlots")
    fun fetchAvailableTimeSlots(): LiveData<ApiResponse<TimeSlotsResponse>>

    @GET("/getAppointmentTypes")
    fun fetchAppointmentTypes(): LiveData<ApiResponse<AppointmentTypeResponse>>
}