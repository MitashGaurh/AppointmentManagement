package com.mitashgaurh.appointmentmanagement.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.ItemPaymentHistoryBinding
import com.mitashgaurh.appointmentmanagement.db.entity.PaymentHistory
import com.mitashgaurh.appointmentmanagement.view.common.DataBoundListAdapter

class PaymentHistoryAdapter(appExecutors: AppExecutors) :
    DataBoundListAdapter<PaymentHistory, ItemPaymentHistoryBinding>(
        mAppExecutors = appExecutors,
        mDiffCallback = object : DiffUtil.ItemCallback<PaymentHistory>() {
            override fun areItemsTheSame(
                oldItem: PaymentHistory,
                newItem: PaymentHistory
            ): Boolean {
                return oldItem.paymentId == newItem.paymentId
            }

            override fun areContentsTheSame(
                oldItem: PaymentHistory,
                newItem: PaymentHistory
            ): Boolean {
                return oldItem.paymentId == newItem.paymentId
                        && oldItem.appointmentDate.time == newItem.appointmentDate.time
            }
        }
    ) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemPaymentHistoryBinding {
        val binding = DataBindingUtil.inflate<ItemPaymentHistoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_payment_history,
            parent,
            false
        )

        binding.root.setOnClickListener {

        }
        return binding
    }

    override fun bind(binding: ItemPaymentHistoryBinding, item: PaymentHistory) {
        binding.paymentHistory = item
    }

}