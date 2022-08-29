package com.jason.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.jason.weather.databinding.ActivityMainBinding
import com.jason.weather.ui.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WeatherApplication.appComponent.inject(this)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .also {
                it.viewPager.adapter = SectionsPagerAdapter(this)
                TabLayoutMediator(it.tabs, it.viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "Current Weather Condition"
                        else -> tab.text = "UpComing 10 day forecast"
                    }
                }.attach()
            }
    }
}