package com.eshc.feature.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.core.ui.view.FilterSpinner
import com.eshc.feature.issue.databinding.FragmentIssueBinding
import com.eshc.feature.issue.model.IssueOptionModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueFragment : Fragment() {
    private var _binding: FragmentIssueBinding? = null
    private val binding get() = _binding

    private val viewModel: IssueViewModel by viewModels()

    private val issueAdapter : IssueAdapter by lazy {
        IssueAdapter()
    }

    private val filterSpinnerAdapter : FilterSpinnerAdapter by lazy {
        FilterSpinnerAdapter(requireContext(),listOf(IssueOptionModel("Open",true),IssueOptionModel("Close",false)))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIssueBinding.inflate(
            inflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner

        binding?.let {
            initRecyclerView(it.rvIssue)
            initSpinner(it.spFilter)
        }

        viewModel.getIssues()

        initObserver()
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = issueAdapter
    }

    private fun initObserver() {
        viewModel.issues.observe(viewLifecycleOwner){
            issueAdapter.submitData(lifecycle,it)
        }
    }

    private fun initSpinner(spinner: FilterSpinner) {
        spinner.setSpinnerAdapter(filterSpinnerAdapter)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        val TAG = this.toString()
    }
}