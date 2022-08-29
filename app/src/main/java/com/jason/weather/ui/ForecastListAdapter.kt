package com.jason.weather.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jason.weather.R
import com.jason.weather.databinding.ItemForecastBinding
import com.jason.weather.model.WeatherUiData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastListAdapter @Inject constructor() : RecyclerView.Adapter<ForecastItemHolder>() {

    private val list: MutableList<WeatherUiData> = MutableList(10) { WeatherUiData.emptyUiData }

    fun updateList(newList: List<WeatherUiData>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastItemHolder =
        ForecastItemHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_forecast, parent, false))

    override fun onBindViewHolder(holder: ForecastItemHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class ForecastItemHolder(
    private val binding: ItemForecastBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(uiData: WeatherUiData) {
        binding.data = uiData
    }
}