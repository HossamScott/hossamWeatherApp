package com.hossam.weatherapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hossam.weatherapp.R
import com.hossam.weatherapp.data.models.CurrentLocationModel
import com.hossam.weatherapp.data.models.ForecastModel
import com.hossam.weatherapp.data.models.SearchModel
import com.hossam.weatherapp.databinding.ActivityMainBinding
import com.hossam.weatherapp.ui.adapters.SearchAdapter
import com.hossam.weatherapp.ui.adapters.WeatherAdapter
import com.hossam.weatherapp.utils.ConstantsObject.DEFAULT_CITY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.days_list.*


class MainActivity : AppCompatActivity(), View.OnClickListener, MainActivityPresenter.View,
    SearchAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mPresenter: MainActivityPresenter
    lateinit var recyclerViewAdapter: WeatherAdapter
    lateinit var searchadapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        mPresenter.getCurrentLocation(DEFAULT_CITY)
    }

    private fun initView() {
        binding.searchIcon.setOnClickListener(this)
        binding.clearIcon.setOnClickListener(this)
        binding.backIcon.setOnClickListener(this)
        mPresenter = MainActivityPresenter(this)


        recyclerview.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        recyclerViewAdapter = WeatherAdapter()
        recyclerview.adapter = recyclerViewAdapter
        searchRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        searchadapter = SearchAdapter()
        searchRecycler.adapter = searchadapter
        searchadapter.setOnItemClickListener(this)

        mPresenter.getCurrentLocation(DEFAULT_CITY) // default city is cairo when the user open the app
        mPresenter.getForecastData(DEFAULT_CITY) // default city is cairo when the user open the app

        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.searchEt.text.toString().trim().length > 1) { // call api if the user type more than 2 chr
                    searchProgress.visibility = View.VISIBLE
                    mPresenter.getSearchData(binding.searchEt.text.toString().trim())
                } else if (binding.searchEt.text.toString().trim().isEmpty()) { // hide city drop down if the user clear search bar
                    searchRecycler.visibility = View.GONE
                    searchProgress.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            (R.id.searchIcon) -> {
                binding.searchLayout.visibility = View.VISIBLE
                binding.searchIcon.visibility = View.GONE
            }
            (R.id.backIcon) -> {
                binding.searchLayout.visibility = View.GONE
                binding.searchIcon.visibility = View.VISIBLE
            }
            (R.id.clearIcon) -> {
                binding.searchEt.text.clear()
            }
        }
    }


    /*
    getting results from current location info and setting its data in UI
     */
    override fun getViewCurrentLocation(currentLocation: CurrentLocationModel?) {
        if (currentLocation != null) {
            binding.timeText.text = currentLocation!!.location.time
            binding.cityText.text = currentLocation.location.name
            binding.dateText.text = currentLocation.location.date
            binding.tempText.text = currentLocation.current.current_temp_f
            binding.dayCondationText.text = currentLocation.current.condition.condation
            binding.tvWind.text = currentLocation.current.windspeed
            binding.tvHumidity.text = currentLocation.current.current_humidity
            Glide.with(binding.tempIcon)
                .load(currentLocation.current.condition.current_icon)
                .into(binding.tempIcon)
        }
    }

    /*
    getting results from search API and setting search list adapter
     */
    @SuppressLint("NotifyDataSetChanged")
    override fun getViewSearch(searchData: SearchModel?) {
        searchProgress.visibility = View.GONE
        searchRecycler.visibility = View.VISIBLE
        if (searchData != null && searchData.isNotEmpty()) {
            searchadapter.setlistData(searchData)
            searchadapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun getViewForecast(forecastData: ForecastModel?) {
        adapterProgress.visibility = View.GONE;
        if (forecastData != null) {
            recyclerViewAdapter.setlistData(forecastData.forecast.forecastday)
            recyclerViewAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, getString(R.string.apiError), Toast.LENGTH_SHORT).show()
        }
    }

    /*
    onClick search adapter (city name) update the UI
     */
    override fun onItemClick(name: String) {
        hideKeyboard(this) // hide keyboard after user click on city
        binding.searchEt.clearFocus() // clear keyboard focus after user click on city
        mPresenter.getCurrentLocation(name)
        mPresenter.getForecastData(name)
        searchRecycler.visibility = View.GONE
        searchLayout.visibility = View.GONE
        searchIcon.visibility = View.VISIBLE
        binding.searchEt.text.clear()
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}