package com.eshc.feature.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eshc.feature.home.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() , TabLayout.OnTabSelectedListener{
    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )

        binding?.lifecycleOwner = this

        binding?.let {
            initTabLayout(it.tlHome)
        }
    }

    private fun initTabLayout(tabLayout: TabLayout) {

        HomeTab.values().forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it.name))
        }

        tabLayout.addOnTabSelectedListener(this)
        tabLayout.selectTab(tabLayout.getTabAt(-1))

        when(viewModel.uiState.value?.selectedTab) {
            HomeTab.Issue -> tabLayout.selectTab(tabLayout.getTabAt(HomeTab.values().indexOf(HomeTab.Issue)))
            HomeTab.Notification -> tabLayout.selectTab(tabLayout.getTabAt(HomeTab.values().indexOf(HomeTab.Notification)))
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewModel.updateSelectedTab(tab?.text.toString())
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }
}