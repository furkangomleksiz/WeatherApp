package com.example.retrofittraining

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.databinding.RecyclerRowBinding
import com.example.retrofittraining.model.Weather

class WeatherAdapter(val cityNames: ArrayList<Weather>): RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {
    class WeatherHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherHolder(binding)
    }

    override fun getItemCount(): Int {
        return cityNames.size
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.binding.locationNameText.text = cityNames.get(position).name
        holder.binding.weatherText.text = cityNames.get(position).desc
        holder.binding.tempText.text = cityNames.get(position).temp.toString()

    }
}