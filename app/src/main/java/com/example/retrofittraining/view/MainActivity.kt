package com.example.retrofittraining.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.WeatherAdapter
import com.example.retrofittraining.databinding.ActivityMainBinding
import com.example.retrofittraining.model.OpenWeatherMapService
import com.example.retrofittraining.model.Weather
import com.example.retrofittraining.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val apiKey = "601d9f0552e280d38404240a0770c994"
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private lateinit var recyclerView: RecyclerView
    private lateinit var weatherList: ArrayList<Weather>
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        recyclerView = binding.recyclerView

        val cityNames = arrayListOf("İstanbul", "Berlin", "London", "New Amsterdam", "Mabaruma")
        weatherList = ArrayList<Weather>()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(OpenWeatherMapService::class.java)
        for(cityName in cityNames) {
            val call = service.getCurrentWeather(cityName, apiKey, "metric")

            call.enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful) {
                        val weatherData = response.body()
                        if (weatherData != null) {
                            val temperature = weatherData.main.temp
                            val weatherDescription = weatherData.weather[0].description
                            println(weatherDescription)
                            println(temperature)
                            val weather = Weather(cityName, temperature, weatherDescription)
                            weatherList.add(weather)
                            weatherAdapter.notifyDataSetChanged()
                        } else {
                            println("error")
                        }
                    } else {
                        println("error")
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    println("error")
                }
            })
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(weatherList)
        binding.recyclerView.adapter = weatherAdapter

    }
}