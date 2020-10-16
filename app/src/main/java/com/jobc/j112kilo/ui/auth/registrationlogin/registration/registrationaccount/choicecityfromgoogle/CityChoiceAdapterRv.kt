package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle.data.model.CitySearchHistory
import kotlinx.android.synthetic.main.fragment_choice_city_from_google_item.view.*

class CityChoiceAdapterRv (
    private val citiesFound: List<CitySearchHistory>,
    private val context: FragmentActivity?,
    private val  clickListener: (CitySearchHistory) -> Unit
) : RecyclerView.Adapter<CityChoiceAdapterRv.ChoiceCityHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceCityHolder =
        ChoiceCityHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_choice_city_from_google_item, parent, false))

    override fun onBindViewHolder(holder: ChoiceCityHolder, position: Int) {
        holder.bind(citiesFound[position], context, clickListener)
    }

    override fun getItemCount(): Int = citiesFound.size

     class  ChoiceCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(
            citySearchHistory: CitySearchHistory,
            context: FragmentActivity?,
            clickListener: (CitySearchHistory) -> Unit) {
            with(itemView) {
                context?.let {
                    when(citySearchHistory.history) {
                        true -> ivHistoryCitySearch
                            .setImageDrawable(it.getDrawable(R.drawable.ic_location_update_24))
                        false -> ivHistoryCitySearch
                            .setImageDrawable(it.getDrawable(R.drawable.ic_location_on_24))
                    }
                }
                tvCity.text = citySearchHistory.cityName
                tvCityAndRegion.text = citySearchHistory.cityRegion
                btnSearchCity.setOnClickListener {
                    clickListener(citySearchHistory)
                }
            }
        }
    }

}
