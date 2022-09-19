package com.eshc.feature.notification.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.feature.notification.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment() {
    private var _binding : FragmentNotificationBinding? = null
    private val binding get() = _binding

    private val viewModel : NotificationViewModel by viewModels()

    private val notificationAdapter : NotificationAdapter by lazy {
        NotificationAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(
            inflater,container,false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner

        binding?.let {
            initRecyclerView(it.notificationRecyclerView)
        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = notificationAdapter

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}