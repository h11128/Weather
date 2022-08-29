package com.jason.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jason.weather.R
import com.jason.weather.WeatherApplication
import com.jason.weather.databinding.FragmentCurrentBinding
import com.jason.weather.viewmodel.CurrentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrentFragment : Fragment() {

    @Inject
    lateinit var currentViewModel: CurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        WeatherApplication.appComponent.inject(this)
        return DataBindingUtil.inflate<FragmentCurrentBinding>(inflater, R.layout.fragment_current, container, false)
            .apply {

                viewModel = currentViewModel
                refreshLayout.setOnRefreshListener {
                    currentViewModel.onRefresh()
                        .delay(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate { refreshLayout.isRefreshing = false }
                        .subscribe({
                            Toast.makeText(requireContext(), "Refresh Success", Toast.LENGTH_SHORT).show()
                        }, {
                            Toast.makeText(requireContext(), "Refresh Fail", Toast.LENGTH_SHORT).show()
                            it.printStackTrace()
                        })
                }
            }
            .root
    }

    override fun onResume() {
        super.onResume()
        currentViewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        currentViewModel.onPause()

    }

}