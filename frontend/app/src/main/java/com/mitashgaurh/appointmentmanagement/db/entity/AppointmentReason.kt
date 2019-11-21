package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment_reason")
data class AppointmentReason(
    @PrimaryKey
    val reasonId: Long,
    val reason: String
) {
    override fun toString(): String {
        return reason
    }
}