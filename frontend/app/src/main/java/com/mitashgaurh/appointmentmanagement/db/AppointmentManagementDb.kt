package com.mitashgaurh.appointmentmanagement.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentHistoryDao
import com.mitashgaurh.appointmentmanagement.db.dao.AppointmentTypeDao
import com.mitashgaurh.appointmentmanagement.db.dao.TimeSlotDao
import com.mitashgaurh.appointmentmanagement.db.dao.UserDao
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot
import com.mitashgaurh.appointmentmanagement.db.entity.User

@Database(
    entities = [User::class,
        AppointmentHistory::class,
        TimeSlot::class,
        AppointmentType::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppointmentManagementDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun appointmentHistoryDao(): AppointmentHistoryDao

    abstract fun timeSlotDao(): TimeSlotDao

    abstract fun appointmentTypeDao(): AppointmentTypeDao
}