package com.eshc.feature.notification.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.feature.notification.databinding.ItemNotificationBinding
import com.eshc.feature.notification.model.NotificationModel

class NotificationAdapter : ListAdapter<NotificationModel, NotificationAdapter.NotificationViewHolder>(
    NotificationDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NotificationViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotificationModel) {
            binding.apply {
                this.notification = item
                executePendingBindings()
            }
        }
    }

    private class NotificationDiffCallback : DiffUtil.ItemCallback<NotificationModel>() {
        override fun areItemsTheSame(oldItem: NotificationModel, newItem: NotificationModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NotificationModel, newItem: NotificationModel): Boolean {
            return oldItem == newItem
        }
    }
}