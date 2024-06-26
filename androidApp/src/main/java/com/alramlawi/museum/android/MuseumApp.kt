package com.alramlawi.museum.android

import android.app.Application
import com.alramlawi.shared.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
