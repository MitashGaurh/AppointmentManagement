package com.mitashgaurh.appointmentmanagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory

/**
 * Interface for database access for Appointment History related operations.
 */
@Dao
interface AppointmentHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appointmentHistory: AppointmentHistory)

    @Query("SELECT * FROM appointment_history")
    fun loadAppointmentHistory(): LiveData<List<AppointmentHistory>>
}