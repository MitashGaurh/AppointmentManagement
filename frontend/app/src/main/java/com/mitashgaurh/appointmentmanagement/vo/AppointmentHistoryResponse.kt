package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory

data class AppointmentHistoryResponse(
    val appointmentHistory: List<AppointmentHistory>
)