package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @field:SerializedName("student_id")
    val studentId: String,
    @field:SerializedName("first_name")
    val firstName: String,
    @field:SerializedName("last_name")
    val lastName: String,
    @field:SerializedName("email_id")
    val emailId: String,
    @field:SerializedName("mobile_number")
    val mobileNumber: String,
    @field:SerializedName("address")
    val address: String,
    @field:SerializedName("date_of_birth")
    val dateOfBirth: Date
)