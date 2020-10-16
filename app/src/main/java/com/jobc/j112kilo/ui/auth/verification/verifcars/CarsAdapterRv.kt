package com.jobc.j112kilo.ui.auth.verification.verifcars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.Car
import kotlinx.android.synthetic.main.cars_fragment_item.view.*

class CarsAdapterRv(
    private val listCar: List<Car>,
    private val context: FragmentActivity?,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<CarsAdapterRv.CarsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsHolder =
        CarsHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.cars_fragment_item, parent, false))

    override fun onBindViewHolder(holder: CarsHolder, position: Int) {
        holder.bind(listCar[position], context, clickListener)
    }

    override fun getItemCount() = listCar.size

    class CarsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(car: Car, context: FragmentActivity?, clickListener: (String) -> Unit) {
            with(itemView) {
                tvCarModel.text = car.carModel
                tvRegistrationNumber.text = car.carRegistrationNum
                car.carPhoto?.let {
                    ivPhotoCar.setImageBitmap(car.carPhoto)
                }
                btnCar.setOnClickListener {
                    clickListener(car.carId)
                }
            }
        }
    }
}