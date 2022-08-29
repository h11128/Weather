package com.jason.weather.network.response

import com.jason.weather.model.Current
import com.jason.weather.model.Location

data class CurrentWeatherResponse(
    val current: Current,
    val location: Location
)