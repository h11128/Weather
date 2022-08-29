package com.jason.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.jason.weather.repo.WeatherRepository
import com.jason.weather.ui.ForecastListAdapter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val forecastListAdapter: ForecastListAdapter
) : ViewModel() {
    private val forecastConditionList = forecastListAdapter.list

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
                forecastListAdapter.notifyItemRangeChanged(0, forecastConditionList.size)
            },
                {
                    it.printStackTrace()
                })
            .also { compositeDisposable.add(it) }

    fun onResume() {
        compositeDisposable.add(getTenDayWeatherFromNetwork())

    }

    fun onRefresh(): Completable = Completable.fromCallable {
        compositeDisposable.clear()
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