package com.jason.weather.network

import com.jason.weather.network.response.CurrentWeatherResponse
import com.jason.weather.network.response.ForecastWeatherResponse
import com.jason.weather.network.response.FutureWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    // https://api.weatherapi.com/v1/current.json?key=5be1e256ef684b0eaea162507222908&q=43212&aqi=no
    @GET("v1/current.json")
    fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") zipCode: Int,
        @Query("api") showAir: String
    ): Single<CurrentWeatherResponse>

    // http://api.weatherapi.com/v1/forecast.json?key=5be1e256ef684b0eaea162507222908&q=43212&days=10
    @GET("v1/forecast.json")
    fun getForecastWeather(
        @Query("key") key: String,
        @Query("q") zipCode: Int,
        @Query("days") days: Int
    ): Single<ForecastWeatherResponse>

}