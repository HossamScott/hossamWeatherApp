package com.hossam.weatherapp.data.models

data class CurrentLocationModel(
    val current: Current,
    val location: Location
)

data class Current(
    val cloud: Int, // 0
    val condition: Condition,
    val feelslike_c: Double, // 16.0
    val feelslike_f: Double, // 60.8
    val gust_kph: Double, // 31.7
    val gust_mph: Double, // 19.7
    val humidity: Int, // 68
    val is_day: Int, // 0
    val last_updated: String, // 2023-01-18 21:15
    val last_updated_epoch: Int, // 1674069300
    val precip_in: Double, // 0.0
    val precip_mm: Double, // 0.0
    val pressure_in: Double, // 30.21
    val pressure_mb: Double, // 1023.0
    val temp_c: Double, // 16.0
    val temp_f: Double, // 60.8
    val uv: Double, // 1.0
    val vis_km: Double, // 10.0
    val vis_miles: Double, // 6.0
    val wind_degree: Int, // 60
    val wind_dir: String, // ENE
    val wind_kph: Double, // 11.2
    val wind_mph: Double, // 6.9

    var current_temp_f: String = "",
    var windspeed: String,
    var current_humidity: String
)

data class Location(
    val country: String, // Egypt
    val lat: Double, // 30.05
    val localtime: String, // 2023-01-18 21:22
    val localtime_epoch: Int, // 1674069734
    val lon: Double, // 31.25
    val name: String, // Cairo
    val region: String, // Al Qahirah
    val tz_id: String, // Africa/Cairo
    var time: String ,
    var date: String
)

data class Condition(
    val code: Int, // 1000
    val icon: String, // //cdn.weatherapi.com/weather/64x64/night/113.png
    val text: String, // Clear
    var condation: String,
    var current_icon: String
)