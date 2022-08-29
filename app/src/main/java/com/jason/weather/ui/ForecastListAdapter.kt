package com.jason.weather.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jason.weather.R
import com.jason.weather.databinding.ItemForecastBinding
import com.jason.weather.model.Condition
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastListAdapter @Inject constructor() : RecyclerView.Adapter<ForecastItemHolder>() {

    val list: MutableList<Condition> = MutableList(10) { Condition.emptyCondition }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastItemHolder =
        ForecastItemHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_forecast, parent, false))

    override fun onBindViewHolder(holder: ForecastItemHolder, position: Int) {
        holder.bind(list[position].toString())
    }

    override fun getItemCount(): Int = list.size
}

class ForecastItemHolder(
    private val binding: ItemForecastBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String) {
        binding.itemText.text = text
    }
}