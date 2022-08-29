package com.jason.weather.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.jason.weather.model.Condition
import com.jason.weather.repo.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val forecastConditionList = MutableList(10) { Condition.emptyCondition }
    val isRefreshing = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()


    fun getTenDayWeatherFromNetwork() =
        weatherRepository.getFutureWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.forecast.forecastday.map { forecastday ->
                    forecastday.day.condition
                }

            }
            .subscribe({
                           forecastConditionList.clear()
                           forecastConditionList.addAll(it)
                       },
                       { it.printStackTrace() })
            .also { compositeDisposable.add(it) }

    fun onResume() {
        isRefreshing.set(false)
        compositeDisposable.add(getTenDayWeatherFromNetwork())

    }

    fun onPause() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        onPause()
    }

}