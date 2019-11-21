package com.mitashgaurh.appointmentmanagement.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mitashgaurh.appointmentmanagement.db.dao.*
import com.mitashgaurh.appointmentmanagement.db.entity.*

@Database(
    entities = [User::class,
        AppointmentHistory::class,
        TimeSlot::class,
        AppointmentType::class,
        AppointmentReason::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppointmentManagementDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun appointmentHistoryDao(): AppointmentHistoryDao

    abstract fun timeSlotDao(): TimeSlotDao

    abstract fun appointmentTypeDao(): AppointmentTypeDao

    abstract fun appointmentReasonDao(): AppointmentReasonDao
}