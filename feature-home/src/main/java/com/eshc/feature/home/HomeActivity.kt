package com.eshc.feature.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.eshc.feature.home.databinding.ActivityHomeBinding
import com.eshc.feature.issue.IssueFragment
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
        viewModel.homeTabState.observe(this){ tab ->
            showFragment(tab)
        }

        viewModel.homeUserImageState.observe(this){ imageUrl ->
            if(imageUrl.isNotBlank()){
                invalidateOptionsMenu()
            }
        }
    }

    private fun showFragment(homeTab: HomeTab) {
        val targetFragment = supportFragmentManager.findFragmentByTag(getFragmentTag(homeTab))
        if(targetFragment == null) {
            supportFragmentManager.commit {
                binding?.flHome?.let {
                    add(it.id,getFragment(homeTab),getFragmentTag(homeTab))
                }
            }
        } else {
            supportFragmentManager.findFragmentByTag(getFragmentTag(homeTab))?.let { fragment ->
                supportFragmentManager.commit {
                    show(fragment)
                }
            }
        }
    }

    private fun hideFragment(homeTab: HomeTab?) {
        supportFragmentManager.findFragmentByTag(getFragmentTag(homeTab))?.let { fragment ->
            supportFragmentManager.commit {
                hide(fragment)
            }
        }
    }

    private fun getFragment(homeTab: HomeTab) : Fragment {
        return when(homeTab) {
            HomeTab.Issue -> IssueFragment()
            HomeTab.Notification -> NotificationFragment()
        }
    }

    private fun getFragmentTag(homeTab: HomeTab?) : String {
        return when(homeTab) {
            HomeTab.Issue -> IssueFragment.TAG
            HomeTab.Notification -> NotificationFragment.TAG
            else -> IssueFragment.TAG
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
        hideFragment(viewModel.homeTabState.value)
        viewModel.updateSelectedTab(tab?.text.toString())
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val url = viewModel.homeUserImageState.value
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
        return true
    }
}