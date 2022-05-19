package com.msf.lordoftherings.core

import android.app.Application
import com.msf.lordoftherings.di.LORDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LORApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LORApplication)
            modules(LORDi.module)
        }
    }

}