package com.mitashgaurh.appointmentmanagement.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mitashgaurh.appointmentmanagement.db.dao.UserDao
import com.mitashgaurh.appointmentmanagement.db.entity.User

@Database(
    entities = [
        User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppointmentManagementDb : RoomDatabase() {

    abstract fun userDao(): UserDao
}