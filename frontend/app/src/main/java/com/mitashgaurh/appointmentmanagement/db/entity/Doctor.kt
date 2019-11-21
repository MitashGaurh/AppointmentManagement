package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doctor")
data class Doctor(
    @PrimaryKey
    @field:SerializedName("doctorId")
    val doctorId: Long,
    @field:SerializedName("doctorName")
    val doctorName: String,
    @field:SerializedName("specialization")
    val specialization: String,
    @field:SerializedName("officeHours")
    val officeHours: String
)