package com.hossam.weatherapp.data.models

class SearchModel : ArrayList<SearchModelItem>()

data class SearchModelItem(
    val country: String, // United States of America
    val id: Int, // 2548773
    val lat: Double, // 34.05
    val lon: Double, // -118.24
    val name: String, // Los Angeles
    val region: String, // California
    val url: String // los-angeles-california-united-states-of-america
)