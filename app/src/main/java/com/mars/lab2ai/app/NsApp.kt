package com.mars.lab2ai.app

import android.app.Application
import com.mars.lab2ai.app.di.PresentationModule
import com.mars.lab2ai.app.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NsApp)
            modules(DataModule, PresentationModule)
        }
    }

}