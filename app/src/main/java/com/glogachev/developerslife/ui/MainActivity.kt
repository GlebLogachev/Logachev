package com.glogachev.developerslife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glogachev.developerslife.App
import com.glogachev.developerslife.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        App.component.inject(this)

        connectPagerAdapter()
    }

    private fun connectPagerAdapter() {
        binding.pager.adapter = FragmentsStateAdapter(this)
        binding.pager.isUserInputEnabled = false
        binding.pager.offscreenPageLimit = 2

        TabLayoutMediator(
            binding.tabs,
            binding.pager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = PostPages.values()[position].title
                }
            }).attach()
    }
}