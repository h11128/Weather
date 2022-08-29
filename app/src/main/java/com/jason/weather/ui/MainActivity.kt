package com.jason.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.jason.weather.R
import com.jason.weather.WeatherApplication
import com.jason.weather.databinding.ActivityMainBinding
import com.jason.weather.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WeatherApplication.appComponent.inject(this)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .also {
                it.viewPager.adapter = SectionsPagerAdapter(this)
                TabLayoutMediator(it.tabs, it.viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "Current"
                        else -> tab.text = "UpComing"
                    }
                }.attach()
                it.viewModel = viewModel
            }

//        val fab: FloatingActionButton = binding.fab
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }
}