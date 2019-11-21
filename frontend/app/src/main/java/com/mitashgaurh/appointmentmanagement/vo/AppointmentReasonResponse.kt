package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentReason

data class AppointmentReasonResponse(
    val appointmentReasons: List<AppointmentReason>
)