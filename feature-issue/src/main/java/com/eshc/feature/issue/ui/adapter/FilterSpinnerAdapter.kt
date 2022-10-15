package com.eshc.feature.issue.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.eshc.domain.model.IssueState
import com.eshc.feature.issue.R
import com.eshc.feature.issue.databinding.ItemSpinnerBinding
import com.eshc.feature.issue.databinding.ItemSpinnerHeaderBinding

class FilterSpinnerAdapter(
    context : Context,
    private val items : List<IssueState>
) : ArrayAdapter<IssueState>(context, R.layout.item_spinner) {

    private var selectedItem : IssueState = IssueState.open

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    fun selectItem(state : IssueState) {
        selectedItem = state
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.issueState = getItem(position)
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.issueState = getItem(position)
        binding.ivCheck.visibility = if(selectedItem == getItem(position)) View.VISIBLE else View.INVISIBLE
        return binding.root
    }

}