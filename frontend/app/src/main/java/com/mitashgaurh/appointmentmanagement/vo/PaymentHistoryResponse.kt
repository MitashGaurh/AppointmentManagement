package com.mitashgaurh.appointmentmanagement.vo

import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory

data class PaymentHistoryResponse(
    val paymentHistory: List<PaymentHistory>
)