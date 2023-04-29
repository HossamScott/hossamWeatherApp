package com.hossam.weatherapp.data.models


data class ForecastModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
) {
    data class Current(
        val cloud: Int, // 0
        val condition: Condition,
        val feelslike_c: Double, // 14.4
        val feelslike_f: Double, // 57.8
        val gust_kph: Double, // 23.0
        val gust_mph: Double, // 14.3
        val humidity: Int, // 82
        val is_day: Int, // 0
        val last_updated: String, // 2023-01-19 00:00
        val last_updated_epoch: Int, // 1674079200
        val precip_in: Double, // 0.0
        val precip_mm: Double, // 0.0
        val pressure_in: Double, // 30.21
        val pressure_mb: Double, // 1023.0
        val temp_c: Double, // 15.0
        val temp_f: Double, // 59.0
        val uv: Double, // 1.0
        val vis_km: Double, // 10.0
        val vis_miles: Double, // 6.0
        val wind_degree: Int, // 70
        val wind_dir: String, // ENE
        val wind_kph: Double, // 11.2
        val wind_mph: Double // 6.9
    ) {
        data class Condition(
            val code: Int, // 1000
            val icon: String, // //cdn.weatherapi.com/weather/64x64/night/113.png
            val text: String // Clear
        )
    }

    data class Forecast(
        val forecastday: List<Forecastday>
    ) {
        data class Forecastday(
            val astro: Astro,
            val date: String, // 2023-01-19
            val date_epoch: Int, // 1674086400
            val day: Day,
            val hour: List<Hour>
        ) {
            data class Astro(
                val moon_illumination: String, // 12
                val moon_phase: String, // Waning Crescent
                val moonrise: String, // 04:23 AM
                val moonset: String, // 02:33 PM
                val sunrise: String, // 06:51 AM
                val sunset: String // 05:21 PM
            )

            data class Day(
                val avghumidity: Double, // 46.0
                val avgtemp_c: Double, // 17.5
                val avgtemp_f: Double, // 63.6
                val avgvis_km: Double, // 10.0
                val avgvis_miles: Double, // 6.0
                val condition: Condition,
                val daily_chance_of_rain: Int, // 0
                val daily_chance_of_snow: Int, // 0
                val daily_will_it_rain: Int, // 0
                val daily_will_it_snow: Int, // 0
                val maxtemp_c: Double, // 25.5
                val maxtemp_f: Double, // 77.9
                val maxwind_kph: Double, // 18.0
                val maxwind_mph: Double, // 11.2
                val mintemp_c: Double, // 11.9
                val mintemp_f: Double, // 53.4
                val totalprecip_in: Double, // 0.0
                val totalprecip_mm: Double, // 0.0
                val totalsnow_cm: Double, // 0.0
                val uv: Double // 5.0
            ) {
                data class Condition(
                    val code: Int, // 1000
                    val icon: String, // //cdn.weatherapi.com/weather/64x64/day/113.png
                    val text: String // Sunny
                )
            }

            data class Hour(
                val chance_of_rain: Int, // 0
                val chance_of_snow: Int, // 0
                val cloud: Int, // 0
                val condition: Condition,
                val dewpoint_c: Double, // 5.5
                val dewpoint_f: Double, // 41.9
                val feelslike_c: Double, // 12.8
                val feelslike_f: Double, // 55.0
                val gust_kph: Double, // 24.5
                val gust_mph: Double, // 15.2
                val heatindex_c: Double, // 13.8
                val heatindex_f: Double, // 56.8
                val humidity: Int, // 57
                val is_day: Int, // 0
                val precip_in: Double, // 0.0
                val precip_mm: Double, // 0.0
                val pressure_in: Double, // 30.18
                val pressure_mb: Double, // 1022.0
                val temp_c: Double, // 13.8
                val temp_f: Double, // 56.8
                val time: String, // 2023-01-19 00:00
                val time_epoch: Int, // 1674079200
                val uv: Double, // 1.0
                val vis_km: Double, // 10.0
                val vis_miles: Double, // 6.0
                val will_it_rain: Int, // 0
                val will_it_snow: Int, // 0
                val wind_degree: Int, // 57
                val wind_dir: String, // ENE
                val wind_kph: Double, // 13.3
                val wind_mph: Double, // 8.3
                val windchill_c: Double, // 12.8
                val windchill_f: Double // 55.0
            ) {
                data class Condition(
                    val code: Int, // 1000
                    val icon: String, // //cdn.weatherapi.com/weather/64x64/night/113.png
                    val text: String // Clear
                )
            }
        }
    }

    data class Location(
        val country: String, // Egypt
        val lat: Double, // 30.05
        val localtime: String, // 2023-01-19 0:07
        val localtime_epoch: Int, // 1674079642
        val lon: Double, // 31.25
        val name: String, // Cairo
        val region: String, // Al Qahirah
        val tz_id: String // Africa/Cairo
    )
}