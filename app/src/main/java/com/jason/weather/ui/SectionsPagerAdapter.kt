package com.jason.weather.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(
    fmActivity: FragmentActivity
) : FragmentStateAdapter(fmActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> CurrentFragment()
        else -> UpComingFragment()
    }


}