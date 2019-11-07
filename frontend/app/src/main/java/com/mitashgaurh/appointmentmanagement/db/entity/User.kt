package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user")
data class User(

    @PrimaryKey
    val studentId: Long,

    val firstName: String,

    val lastName: String,

    val emailId: String,

    val mobileNumber: String,

    val address: String,

    val dateOfBirth: Date
)