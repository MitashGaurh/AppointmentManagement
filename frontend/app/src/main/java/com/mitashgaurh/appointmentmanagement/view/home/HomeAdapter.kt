package com.mitashgaurh.appointmentmanagement.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.ItemAppointmentHistoryBinding
import com.mitashgaurh.appointmentmanagement.db.entity.AppointmentHistory
import com.mitashgaurh.appointmentmanagement.view.common.DataBoundListAdapter

class HomeAdapter(appExecutors: AppExecutors) :
    DataBoundListAdapter<AppointmentHistory, ItemAppointmentHistoryBinding>(
        mAppExecutors = appExecutors,
        mDiffCallback = object : DiffUtil.ItemCallback<AppointmentHistory>() {
            override fun areItemsTheSame(
                oldItem: AppointmentHistory,
                newItem: AppointmentHistory
            ): Boolean {
                return oldItem.appointmentId == newItem.appointmentId
            }

            override fun areContentsTheSame(
                oldItem: AppointmentHistory,
                newItem: AppointmentHistory
            ): Boolean {
                return oldItem.appointmentId == newItem.appointmentId
                        && oldItem.doctorName == newItem.doctorName
            }
        }
    ) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemAppointmentHistoryBinding {
        val binding = DataBindingUtil.inflate<ItemAppointmentHistoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_appointment_history,
            parent,
            false
        )

        binding.root.setOnClickListener {

        }
        return binding
    }

    override fun bind(binding: ItemAppointmentHistoryBinding, item: AppointmentHistory) {
        binding.appointmentHistory = item
    }

}