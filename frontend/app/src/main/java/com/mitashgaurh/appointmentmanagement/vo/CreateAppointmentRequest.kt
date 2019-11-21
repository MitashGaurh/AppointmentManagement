package com.mitashgaurh.appointmentmanagement.vo

import java.util.Date

data class CreateAppointmentRequest(
    val studentId: String,
    val slotId: Int,
    val typeId: Int,
    val reasonId: Int,
    val appointmentDate: Date
)