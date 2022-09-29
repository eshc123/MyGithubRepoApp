package com.eshc.feature.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.eshc.feature.profile.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private var _binding : ActivityProfileBinding? = null
    private val binding get() = _binding

    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,R.layout.activity_profile
        )
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        viewModel.getUser()

        initObserver()
    }

    private fun initObserver() {
        viewModel.uiState.observe(this) {
            if(it.error.isNotBlank()){
                Toast.makeText(this,it.error,Toast.LENGTH_SHORT).show()
            }
        }
    }
}