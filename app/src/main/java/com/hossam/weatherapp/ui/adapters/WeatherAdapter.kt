package com.hossam.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hossam.weatherapp.R
import com.hossam.weatherapp.data.models.ForecastModel
import kotlinx.android.synthetic.main.days_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    private var listData: List<ForecastModel.Forecast.Forecastday>? = null

    fun setlistData(listData: List<ForecastModel.Forecast.Forecastday>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.days_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbImage = view.thumbImage
        val tvTemp = view.tvTemp
        val tvDay = view.tvDay

        fun bind(data: ForecastModel.Forecast.Forecastday) {
            tvTemp.text = data.day.mintemp_f.toString() + "/" + data.day.maxtemp_f.toString()+"°F"
            tvDay.text = getDay(data.date)
            Glide.with(thumbImage)
                .load(data.day.condition.icon.replace("//", "https://"))
                .into(thumbImage)
        }

        @SuppressLint("SimpleDateFormat")
        fun getDay(date: String): String {
            val inFormat = SimpleDateFormat("yyyy-MM-dd")
            val date: Date = inFormat.parse(date)
            val outFormat = SimpleDateFormat("EEEE")
            return outFormat.format(date)
        }
    }

}