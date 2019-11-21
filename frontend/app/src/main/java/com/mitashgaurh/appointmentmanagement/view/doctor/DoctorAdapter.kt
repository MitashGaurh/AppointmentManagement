package com.mitashgaurh.appointmentmanagement.view.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.ItemDoctorBinding
import com.mitashgaurh.appointmentmanagement.db.entity.Doctor
import com.mitashgaurh.appointmentmanagement.view.common.DataBoundListAdapter

class DoctorAdapter(appExecutors: AppExecutors) :
    DataBoundListAdapter<Doctor, ItemDoctorBinding>(
        mAppExecutors = appExecutors,
        mDiffCallback = object : DiffUtil.ItemCallback<Doctor>() {
            override fun areItemsTheSame(
                oldItem: Doctor,
                newItem: Doctor
            ): Boolean {
                return oldItem.doctorId == newItem.doctorId
            }

            override fun areContentsTheSame(
                oldItem: Doctor,
                newItem: Doctor
            ): Boolean {
                return oldItem.doctorId == newItem.doctorId
                        && oldItem.doctorName == newItem.doctorName
            }
        }
    ) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemDoctorBinding {
        val binding = DataBindingUtil.inflate<ItemDoctorBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_doctor,
            parent,
            false
        )

        binding.root.setOnClickListener {

        }
        return binding
    }

    override fun bind(binding: ItemDoctorBinding, item: Doctor) {
        binding.doctor = item
    }

}