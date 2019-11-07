package com.mitashgaurh.appointmentmanagement.api

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.vo.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AppointmentManagementService {

    @FormUrlEncoded
    @POST("/login")
    fun performLogin(
        @Field("studentId") studentId: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<LoginResponse>>
}