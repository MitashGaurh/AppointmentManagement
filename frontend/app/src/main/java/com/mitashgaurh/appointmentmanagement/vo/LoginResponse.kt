package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.User

data class LoginResponse(
    val status: String,
    val userDetails: User
)