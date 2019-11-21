package com.mitashgaurh.appointmentmanagement.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "payment_history")
data class PaymentHistory(
    @PrimaryKey
    @field:SerializedName("paymentId")
    val paymentId: Long,
    @field:SerializedName("appointmentDate")
    val appointmentDate: Date,
    @field:SerializedName("appointmentTime")
    val appointmentTime: String,
    @field:SerializedName("doctorName")
    val doctorName: String,
    @field:SerializedName("amountDue")
    val amountDue: Double,
    @field:SerializedName("paymentStatus")
    val paymentStatus: String,
    @field:SerializedName("paymentType")
    val paymentType: String,
    @field:SerializedName("paymentDate")
    val paymentDate: String
)