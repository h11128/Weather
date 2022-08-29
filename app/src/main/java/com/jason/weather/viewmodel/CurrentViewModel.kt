package com.jason.weather.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jason.weather.repo.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val currentCondition: ObservableField<String> = ObservableField("")
    val isRefreshing = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()

    private fun getCurrentWeatherFromNetwork() =
        weatherRepository.getCurrentWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.current.condition.toString() }
            .subscribe({ currentCondition.set(it) },
                       { it.printStackTrace() })
            .also { compositeDisposable.add(it) }

    fun onResume() {
        isRefreshing.set(false)
        compositeDisposable.add(getCurrentWeatherFromNetwork())

    }

    fun onPause() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        onPause()
    }

}