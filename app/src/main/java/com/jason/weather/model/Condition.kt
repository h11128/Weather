package com.jason.weather.model

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
) {
    companion object {
        val emptyCondition =
            Condition(
                0,
                "",
                "Ops your condition is empty"
            )
    }
}