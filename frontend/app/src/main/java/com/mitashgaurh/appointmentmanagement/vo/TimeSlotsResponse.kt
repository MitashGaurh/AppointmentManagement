package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot

data class TimeSlotsResponse(
    val availableTimeSlots: List<TimeSlot>
)