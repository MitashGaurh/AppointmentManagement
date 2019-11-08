package com.mitashgaurh.appointmentmanagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentType

/**
 * Interface for database access for Appointment Type related operations.
 */
@Dao
interface AppointmentTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appointmentType: AppointmentType)

    @Query("SELECT * FROM appointment_type")
    fun loadAppointmentTypes(): LiveData<List<AppointmentType>>
}