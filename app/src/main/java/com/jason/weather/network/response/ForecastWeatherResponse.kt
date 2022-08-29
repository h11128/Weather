package com.jason.weather.network.response

import com.jason.weather.model.Current
import com.jason.weather.model.Forecast
import com.jason.weather.model.Location

data class ForecastWeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)