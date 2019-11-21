package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment_type")
data class AppointmentType(
    @PrimaryKey
    val typeId: Long,
    val typeName: String
)