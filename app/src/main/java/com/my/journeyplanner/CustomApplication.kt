package com.my.journeyplanner

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}