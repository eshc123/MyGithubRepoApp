package com.eshc.feature.repository.ui

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.feature.repository.R
import com.eshc.feature.repository.databinding.ActivityRepositoryBinding
import com.eshc.feature.repository.ui.adapter.RepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class RepositoryActivity : AppCompatActivity() {
    private var _binding : ActivityRepositoryBinding? = null
    private val binding get() = _binding

    private val viewModel : RepositoryViewModel by viewModels()

    private val repoAdapter : RepositoryAdapter by lazy {
        RepositoryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_repository
        )
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        binding?.let {
            initRecyclerView(it.rvRepository)
            initEditText(it.etSearch)
        }

        initObserver()
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = repoAdapter
    }

    private fun initObserver() {
        viewModel.repos.observe(this) {
            repoAdapter.submitData(lifecycle,it)
        }

    }

    private fun initEditText(editText: EditText) {
        editText.addTextChangedListener {
            viewModel.setSearchWord(it.toString())
        }
    }
}