package com.example.ticktock

import android.app.Application
import com.example.ticktock.di.boardModule
import com.example.ticktock.di.gameModule
import com.example.ticktock.di.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    private val appComponent  = mutableListOf(gameModule, boardModule, playerModule)


    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(applicationContext)
            // declare modules
            modules(appComponent)
        }
    }
}