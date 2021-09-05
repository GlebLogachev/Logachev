package com.glogachev.developerslife.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.glogachev.developerslife.ui.pages.hot.HotPageFragment
import com.glogachev.developerslife.ui.pages.latest.LatestPageFragment
import com.glogachev.developerslife.ui.pages.random.RandomPageFragment
import com.glogachev.developerslife.ui.pages.top.TopPageFragment

class FragmentsStateAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = PostPages.values().size


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            PostPages.LATEST.position -> LatestPageFragment()
            PostPages.HOT.position -> HotPageFragment()
            PostPages.TOP.position -> TopPageFragment()
            PostPages.RANDOM.position -> RandomPageFragment()
            else -> error("impossible fragment")
        }
    }
}