package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType

data class AppointmentTypeResponse(
    val appointmentTypes: List<AppointmentType>
)