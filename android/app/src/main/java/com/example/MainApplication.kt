package com.example

import android.app.Application;

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FlutterEngineManager.init(applicationContext)
    }
}