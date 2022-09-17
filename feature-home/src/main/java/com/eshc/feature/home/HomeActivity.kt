package com.eshc.feature.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eshc.feature.home.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {
    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )

        binding?.lifecycleOwner = this

    }
}