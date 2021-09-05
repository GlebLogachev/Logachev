package com.glogachev.developerslife.di

import android.content.Context
import com.glogachev.developerslife.di.modules.AppModule
import com.glogachev.developerslife.di.modules.NetworkModule
import com.glogachev.developerslife.di.modules.RepositoryModule
import com.glogachev.developerslife.ui.MainActivity
import com.glogachev.developerslife.ui.pages.hot.HotPageFragment
import com.glogachev.developerslife.ui.pages.latest.LatestPageFragment
import com.glogachev.developerslife.ui.pages.random.RandomPageFragment
import com.glogachev.developerslife.ui.pages.top.TopPageFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: RandomPageFragment)
    fun inject(target: LatestPageFragment)
    fun inject(target: TopPageFragment)
    fun inject(target: HotPageFragment)
    val context: Context
}