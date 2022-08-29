package com.jason.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jason.weather.R
import com.jason.weather.WeatherApplication
import com.jason.weather.databinding.FragmentUpcomingBinding
import com.jason.weather.viewmodel.ForecastViewModel
import javax.inject.Inject

class UpComingFragment : Fragment() {

    @Inject
    lateinit var forecastViewModel: ForecastViewModel

    @Inject
    lateinit var listAdapter: ForecastListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        WeatherApplication.appComponent.inject(this)
        return DataBindingUtil.inflate<FragmentUpcomingBinding>(inflater, R.layout.fragment_upcoming, container, false)
            .apply {
                viewModel = forecastViewModel
                recyclerView.adapter = listAdapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
            .root
    }

    override fun onResume() {
        super.onResume()
        forecastViewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        forecastViewModel.onPause()
    }

}