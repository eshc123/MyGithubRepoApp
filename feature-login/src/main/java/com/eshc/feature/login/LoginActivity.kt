package com.eshc.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.eshc.feature.login.common.GITHUB_AUTH
import com.eshc.feature.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,R.layout.activity_login
        )

        binding?.lifecycleOwner = this

        binding?.btLogin?.setOnClickListener {
            moveToGithubWeb()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let { data ->
            val code = data.getQueryParameter(getString(R.string.param_code))
            viewModel.getAccessToken(code ?: "")
        }
    }

    private fun moveToGithubWeb(){
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                GITHUB_AUTH.toUri()
            )
        )
    }
}