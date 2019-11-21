package com.mitashgaurh.appointmentmanagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import java.util.*

/**
 * Interface for database access for Appointment History related operations.
 */
@Dao
interface AppointmentHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appointmentHistory: AppointmentHistory)

    @Query("SELECT * FROM appointment_history WHERE appointmentDate >= :date ORDER BY appointmentDate DESC LIMIT 3")
    fun loadUpcomingAppointments(date: Date): LiveData<List<AppointmentHistory>>

    @Query("SELECT * FROM appointment_history ORDER BY appointmentDate DESC")
    fun loadAppointmentHistory(): LiveData<List<AppointmentHistory>>
}