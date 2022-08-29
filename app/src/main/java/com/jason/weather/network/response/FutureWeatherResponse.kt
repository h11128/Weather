package com.jason.weather.network.response

import com.jason.weather.model.Forecast
import com.jason.weather.model.Location

data class FutureWeatherResponse(
    val forecast: Forecast,
    val location: Location
)