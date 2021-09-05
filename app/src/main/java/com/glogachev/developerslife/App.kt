package com.glogachev.developerslife

import android.app.Application
import com.glogachev.developerslife.di.AppComponent
import com.glogachev.developerslife.di.DaggerAppComponent
import com.glogachev.developerslife.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var component : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}