package com.hossam.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hossam.weatherapp.R
import com.hossam.weatherapp.data.models.SearchModel
import com.hossam.weatherapp.data.models.SearchModelItem
import com.hossam.weatherapp.utils.SearchClick
import kotlinx.android.synthetic.main.search_list.view.*


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private var listData: SearchModel? = null
    private var mListener: OnItemClickListener? = null

    fun setlistData(listData: SearchModel) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)

        holder.locationLayout.setOnClickListener {
            /*
            trigger loading weather data in mainActivity
             */
            if (mListener != null) {
                mListener!!.onItemClick(listData?.get(position)!!.name);
            }
        }
    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTemp = view.tvTemp
        val locationLayout = view.locationLayout

        @SuppressLint("SetTextI18n")
        fun bind(data: SearchModelItem) {
            tvTemp.text = data.name + " - " + data.region
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    interface OnItemClickListener {
        fun onItemClick(name: String)
    }
}