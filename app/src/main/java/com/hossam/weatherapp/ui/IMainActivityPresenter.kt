package com.hossam.weatherapp.ui


interface IMainActivityPresenter {

    fun getCurrentLocation (city: String)
    fun getForecastData (q: String)
    fun getSearchData (q: String)
}