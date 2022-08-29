package com.jason.weather.repo

import com.jason.weather.network.WeatherService
import com.jason.weather.network.response.CurrentWeatherResponse
import com.jason.weather.network.response.ForecastWeatherResponse
import com.jason.weather.network.response.FutureWeatherResponse
import com.jason.weather.util.ApiKey
import com.jason.weather.util.ShowAir
import com.jason.weather.util.ZipCode
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    fun getCurrentWeather(): Single<CurrentWeatherResponse> {
        return weatherService.getCurrentWeather(ApiKey, ZipCode, ShowAir)
    }

    fun getFutureWeather(): Single<ForecastWeatherResponse> {
        return weatherService.getForecastWeather(ApiKey, ZipCode, 10)
    }

}