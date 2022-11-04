package com.eshc.feature.repository.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eshc.feature.repository.R
import com.eshc.feature.repository.databinding.ActivityRepositoryBinding

class RepositoryActivity : AppCompatActivity() {
    private var _binding : ActivityRepositoryBinding? = null
    private val binding get() = _binding

    private val viewModel : RepositoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_repository
        )
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

    }
}