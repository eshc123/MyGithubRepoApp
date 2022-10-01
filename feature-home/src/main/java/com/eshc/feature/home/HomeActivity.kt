package com.eshc.feature.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.eshc.feature.home.databinding.ActivityHomeBinding
import com.eshc.feature.notification.ui.NotificationFragment
import com.eshc.feature.profile.ProfileActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        initViews()
        initObserver()
    }

    private fun initViews(){
        binding?.let {
            initTabLayout(it.tlHome)
            initToolbar(it.tbHome)
        }
    }

    private fun initTabLayout(tabLayout: TabLayout) {
        HomeTab.values().forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it.name))
        }

        tabLayout.addOnTabSelectedListener(this)
        tabLayout.selectTab(tabLayout.getTabAt(-1))

    }

    private fun initToolbar(toolbar: MaterialToolbar){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> startProfileActivity()
                R.id.nav_search -> startSearchActivity()
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun initObserver(){
        viewModel.uiState.observe(this) { homeUiState ->
            if(homeUiState.userImage.isNotBlank()){
                invalidateOptionsMenu()
            }

            when(homeUiState.selectedTab){
                HomeTab.Issue -> changeToIssue()
                HomeTab.Notification -> changeToNotification()
            }
        }
    }

    private fun changeToIssue() {

    }

    private fun changeToNotification() {
        binding?.flHome?.let{
            supportFragmentManager.commit {
                replace(it.id,NotificationFragment())
            }
        }
    }

    private fun startProfileActivity(){
        startActivity(
            Intent(
                this, ProfileActivity::class.java
            )
        )
    }

    private fun startSearchActivity(){

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewModel.updateSelectedTab(tab?.text.toString())
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val url = viewModel.uiState.value?.userImage

        Glide.with(this).asDrawable().load(url).transform(CircleCrop())
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    menu?.findItem(R.id.nav_profile)?.let {
                        it.icon = resource
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        return super.onPrepareOptionsMenu(menu)
    }
}