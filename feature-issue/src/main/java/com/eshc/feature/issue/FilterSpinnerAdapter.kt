package com.eshc.feature.issue

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.eshc.feature.issue.databinding.ItemSpinnerBinding
import com.eshc.feature.issue.databinding.ItemSpinnerHeaderBinding
import com.eshc.feature.issue.model.IssueOptionModel

class FilterSpinnerAdapter(
    context : Context,
    private val items : List<IssueOptionModel>
) : ArrayAdapter<IssueOptionModel>(context,R.layout.item_spinner) {

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerHeaderBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.issueOption = getItem(position)
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        binding.issueOption = getItem(position)
        return binding.root
    }

}