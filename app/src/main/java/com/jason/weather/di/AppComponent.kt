package com.jason.weather.di

import com.jason.weather.ui.CurrentFragment
import com.jason.weather.ui.MainActivity
import com.jason.weather.ui.UpComingFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(currentFragment: CurrentFragment)
    fun inject(upComingFragment: UpComingFragment)

}