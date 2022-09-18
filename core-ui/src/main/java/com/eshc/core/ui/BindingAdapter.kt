package com.eshc.core.ui

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.eshc.core.design.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop

@BindingAdapter("iconImage")
fun AppCompatImageView.setIconImage(resource : String){
    when(resource){
        "" -> {
            Glide.with(this.context)
                .load(R.drawable.ic_user)
                .into(this)
        }
        else -> {
            Glide.with(this.context)
                .load(resource)
                .placeholder(R.drawable.ic_user)
                .transform(CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_user)
                .into(this)
        }
    }
}