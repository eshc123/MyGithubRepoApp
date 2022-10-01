package com.eshc.feature.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eshc.feature.issue.databinding.FragmentIssueBinding

class IssueFragment : Fragment() {
    private var _binding: FragmentIssueBinding? = null
    private val binding get() = _binding

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

    companion object {
        val TAG = this.toString()
    }
}