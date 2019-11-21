package com.mitashgaurh.appointmentmanagement.api

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.vo.*
import retrofit2.Call
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("/getPaymentHistory")
    fun fetchPaymentHistory(@Field(value = "studentId") studentId: String): LiveData<ApiResponse<PaymentHistoryResponse>>

    @GET("/getAvailableTimeSlots")
    fun fetchAvailableTimeSlots(): LiveData<ApiResponse<TimeSlotsResponse>>

    @GET("/getAvailableDoctors")
    fun fetchAvailableDoctors(): LiveData<ApiResponse<DoctorResponse>>

    @GET("/getAppointmentTypes")
    fun fetchAppointmentTypes(): LiveData<ApiResponse<AppointmentTypeResponse>>

    @GET("/getAppointmentReasons")
    fun fetchAppointmentReasons(): LiveData<ApiResponse<AppointmentReasonResponse>>

    @POST("/createNewAppointment")
    fun createNewAppointment(@Body createAppointmentRequest: CreateAppointmentRequest): Call<Any>

    @POST("/updateStudentProfile")
    fun updateUserProfile(@Body user: User): Call<Any>
}