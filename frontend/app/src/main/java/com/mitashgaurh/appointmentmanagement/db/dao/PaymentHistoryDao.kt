package com.mitashgaurh.appointmentmanagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory

@Dao
interface PaymentHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paymentHistory: PaymentHistory)

    @Query("SELECT * FROM payment_history WHERE paymentStatus = 'Pending'")
    fun loadPendingPayments(): LiveData<List<PaymentHistory>>
}