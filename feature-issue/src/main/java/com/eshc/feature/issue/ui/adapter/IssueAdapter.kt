package com.eshc.feature.issue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eshc.feature.issue.databinding.ItemIssueBinding
import com.eshc.feature.issue.model.IssueModel

class IssueAdapter : PagingDataAdapter<IssueModel, IssueAdapter.IssueViewHolder>(
    IssueDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        return IssueViewHolder(
            ItemIssueBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class IssueViewHolder(val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IssueModel) {
            binding.apply {
                this.issue = item
                executePendingBindings()
            }
        }
    }

    private class IssueDiffCallback : DiffUtil.ItemCallback<IssueModel>() {
        override fun areItemsTheSame(oldItem: IssueModel, newItem: IssueModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IssueModel, newItem: IssueModel): Boolean {
            return oldItem == newItem
        }
    }
}