package com.eshc.feature.notification.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.feature.notification.databinding.FragmentNotificationBinding
import com.eshc.feature.notification.model.NotificationModel
import com.eshc.feature.notification.ui.adapter.NotificationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding

    private val viewModel: NotificationViewModel by viewModels()

    private val notificationAdapter: NotificationAdapter by lazy {
        NotificationAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(
            inflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner

        binding?.let {
            initRecyclerView(it.notificationRecyclerView)
        }

        viewModel.getNotifications()

        initObserver()
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = notificationAdapter
        ItemTouchHelper(NotificationItemHelper(requireContext()) { notification ->
            removeNotification(notification)
        }).attachToRecyclerView(recyclerView)
    }

    private fun initObserver() {
        viewModel.notifications.observe(viewLifecycleOwner) {
            notificationAdapter.submitData(lifecycle,it)
        }
    }

    private fun removeNotification(notification: NotificationModel) {
        viewModel.removeNotification(notification)
    }

    override fun onStop() {
        if(viewModel.isEmptyNotificationsToBeRemoved.not()) {
            viewModel.removeAllNotifications()
            binding?.notificationRecyclerView?.smoothScrollToPosition(0)
        }
        super.onStop()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        val TAG = this.toString()
    }
}