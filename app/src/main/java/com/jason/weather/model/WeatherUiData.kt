package com.jason.weather.model

data class WeatherUiData(
    val code: String,
    val icon: String,
    val text: String,
    val date: String
) {
    companion object{
        val emptyUiData = WeatherUiData(
            "ops your data is empty", "", "", ""
        )
    }
}

fun Current.toWeatherUiData(): WeatherUiData =
    WeatherUiData(
        "code: ${condition.code}",
        "icon: ${condition.icon}",
        "text: ${condition.text}",
        "lastUpdated: $last_updated"
    )

fun Forecastday.toWeatherUiData(): WeatherUiData =
    WeatherUiData(
        "code: ${day.condition.code}",
        "icon: ${day.condition.icon}",
        "text: ${day.condition.text}",
        "lastUpdated: $date"
    )