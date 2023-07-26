package com.example.retrofittraining.model

data class WeatherData(val main: MainInfo, val weather: List<WeatherInfo>) {
}

data class MainInfo(
    val temp : Double
)

data class WeatherInfo(
    val description: String
)