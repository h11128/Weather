package com.jason.weather

import android.app.Application
import com.jason.weather.di.AppComponent
import com.jason.weather.di.DaggerAppComponent

class WeatherApplication: Application() {

    private var _appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: WeatherApplication
        val appComponent: AppComponent
            get() = instance._appComponent ?: DaggerAppComponent.create()
    }
}