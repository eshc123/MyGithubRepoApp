package com.eshc.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.eshc.core.ui.databinding.LayoutFilterBinding

class FilterSpinner(
    context : Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context,attributeSet) {

    //var adapter : BaseAdapter? = null
    private var _binding : LayoutFilterBinding? = null
    val binding get() = _binding

    init {
        initView()
    }

    fun setSpinnerAdapter(adapter: BaseAdapter){
        binding?.spFilter?.adapter = adapter
    }

    private fun initView(){
        _binding = LayoutFilterBinding.inflate(LayoutInflater.from(context),this,false)
        val constraintSet = ConstraintSet()
        addView(binding?.root)

        constraintSet.clone(this)
        binding?.let {
            constraintSet.match(it.root,this)
        }

    }

    private fun ConstraintSet.match(view: View, parentView : View) {
        this.connect(view.id,ConstraintSet.TOP,parentView.id,ConstraintSet.TOP)
        this.connect(view.id,ConstraintSet.BOTTOM,parentView.id,ConstraintSet.BOTTOM)
        this.connect(view.id,ConstraintSet.START,parentView.id,ConstraintSet.START)
        this.connect(view.id,ConstraintSet.END,parentView.id,ConstraintSet.END)
    }
}