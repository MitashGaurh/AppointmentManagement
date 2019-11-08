package com.mitashgaurh.appointmentmanagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitashgaurh.appointmentmanagement.db.entity.TimeSlot

/**
 * Interface for database access for Time Slot related operations.
 */
@Dao
interface TimeSlotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(timeSlot: TimeSlot)

    @Query("SELECT * FROM time_slot")
    fun loadTimeSlots(): LiveData<List<TimeSlot>>
}