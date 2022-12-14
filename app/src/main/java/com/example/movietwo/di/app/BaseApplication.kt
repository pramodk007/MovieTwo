package com.example.movietwo.di.app

import android.app.Application
import com.example.movietwo.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
@HiltAndroidApp
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initLogger()
    }
    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
                }
            })
        }
    }
}