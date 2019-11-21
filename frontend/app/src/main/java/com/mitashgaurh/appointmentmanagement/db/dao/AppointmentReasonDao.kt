package com.mitashgaurh.appointmentmanagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentReason

/**
 * Interface for database access for Appointment Reason related operations.
 */
@Dao
interface AppointmentReasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appointmentReason: AppointmentReason)

    @Query("SELECT * FROM appointment_reason")
    fun loadAppointmentReasons(): LiveData<List<AppointmentReason>>
}