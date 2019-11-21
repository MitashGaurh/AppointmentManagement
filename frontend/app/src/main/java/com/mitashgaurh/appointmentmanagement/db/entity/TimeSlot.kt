package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "time_slot")
data class TimeSlot(

    @PrimaryKey
    @field:SerializedName("slotId")
    val slotId: Long,
    @field:SerializedName("slotTime")
    val slotTime: String,
    @field:SerializedName("doctorName")
    val doctorName: String,
    @field:SerializedName("appointmentDate")
    val appointmentDate: Date
) {
    override fun toString(): String {
        return "$doctorName $slotTime " + SimpleDateFormat(
            "MM/dd/yyyy",
            Locale.US
        ).format(
            appointmentDate
        )
    }
}