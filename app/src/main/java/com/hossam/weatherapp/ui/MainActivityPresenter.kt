package com.hossam.weatherapp.ui

import android.annotation.SuppressLint
import com.hossam.weatherapp.data.models.CurrentLocationModel
import com.hossam.weatherapp.data.models.ForecastModel
import com.hossam.weatherapp.data.models.SearchModel
import com.hossam.weatherapp.data.network.AppModule
import com.hossam.weatherapp.utils.ConstantsObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivityPresenter(private val view: View) : IMainActivityPresenter {
    override fun getCurrentLocation(city: String) {
        AppModule.create()?.getCurrentLocationData(ConstantsObject.KEY, city)
            ?.enqueue(object : Callback<CurrentLocationModel> {
                override fun onFailure(call: Call<CurrentLocationModel>, t: Throwable) {
                    view.getViewCurrentLocation(null)
                }

                override fun onResponse(
                    call: Call<CurrentLocationModel>,
                    response: Response<CurrentLocationModel>
                ) {
                    val time_date = response.body()?.location?.localtime!!.split(" ").toTypedArray()
                    response.body()!!.location.time = time_date[1]
                    response.body()!!.location.date = getDay(time_date[0]) + ", " + time_date[0]
                    response.body()!!.current.current_temp_f =
                        "" + response.body()!!.current.temp_f.toString() + "°F"
                    response.body()!!.current.condition.condation =
                        "It's a " + response.body()!!.current.condition.text + " day."
                    response.body()!!.current.windspeed =
                        response.body()!!.current.wind_mph.toString() + " mph"
                    response.body()!!.current.current_humidity =
                        response.body()!!.current.humidity.toString() + " %"
                    response.body()!!.current.condition.current_icon =
                        response.body()!!.current.condition.icon.replace("//", "https://")

                    view.getViewCurrentLocation(response.body()!!)
                }
            })
    }


    override fun getForecastData(text: String) {
        AppModule.create()?.getForecastData(ConstantsObject.KEY, text, "3")
            ?.enqueue(object : Callback<ForecastModel> {
                override fun onFailure(call: Call<ForecastModel>, t: Throwable) {
                    view.getViewCurrentLocation(null)
                }

                override fun onResponse(
                    call: Call<ForecastModel>,
                    response: Response<ForecastModel>
                ) {
                    view.getViewForecast(response.body()!!)
                }
            })
    }

    override fun getSearchData(q: String) {
        AppModule.create()?.getSearchData(ConstantsObject.KEY, q)
            ?.enqueue(object : Callback<SearchModel> {
                override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                    view.getViewCurrentLocation(null)
                }

                override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                    view.getViewSearch(response.body()!!)
                }
            })
    }

    interface View {
        fun getViewCurrentLocation(currentLocation: CurrentLocationModel?)
        fun getViewSearch(searchData: SearchModel?)
        fun getViewForecast(forecastData: ForecastModel?)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDay(date: String): String {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = inFormat.parse(date)
        val outFormat = SimpleDateFormat("EEEE")
        return outFormat.format(date)
    }
}