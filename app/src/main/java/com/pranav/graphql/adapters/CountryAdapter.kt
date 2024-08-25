package com.pranav.graphql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pranav.GetCountriesQuery
import com.pranav.graphql.databinding.CountryListCardBinding

class CountryAdapter(private val list: List<GetCountriesQuery.Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CountryListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryName: String) {
            binding.apply {
                countryTxt.text = countryName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        return ViewHolder(
            CountryListCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) {
        holder.bind(list[position].name)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}