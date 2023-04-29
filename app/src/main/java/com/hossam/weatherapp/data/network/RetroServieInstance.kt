package com.hossam.weatherapp.data.network

import com.hossam.weatherapp.data.models.CurrentLocationModel
import com.hossam.weatherapp.data.models.ForecastModel
import com.hossam.weatherapp.data.models.SearchModel
import com.hossam.weatherapp.utils.ConstantsObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServieInstance {

    @GET(ConstantsObject.CURRENT_LOCATION)
    fun getCurrentLocationData(
        @Query("key") key: String,
        @Query("q") location: String
    ): Call<CurrentLocationModel>

    @GET(ConstantsObject.FORECAST)
    fun getForecastData(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: String
    ): Call<ForecastModel>

    @GET(ConstantsObject.SEARCH)
    fun getSearchData(
        @Query("key") key: String,
        @Query("q") location: String
    ): Call<SearchModel>

}