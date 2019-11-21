package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.Doctor

data class DoctorResponse(
    val availableDoctors: List<Doctor>
)