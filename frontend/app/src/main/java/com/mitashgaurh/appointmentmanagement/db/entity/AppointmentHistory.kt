package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "appointment_history")
data class AppointmentHistory(

    @PrimaryKey
    @field:SerializedName("appointmentId")
    val appointmentId: Long,
    @field:SerializedName("studentId")
    val studentId: Long,
    @field:SerializedName("appointmentDate")
    val appointmentDate: Date,
    @field:SerializedName("appointmentTime")
    val appointmentTime: String,
    @field:SerializedName("doctorName")
    val doctorName: String,
    @field:SerializedName("appointmentType")
    val appointmentType: String,
    @field:SerializedName("appointmentReason")
    val appointmentReason: String,
    @field:SerializedName("appointmentStatus")
    val appointmentStatus: String
)