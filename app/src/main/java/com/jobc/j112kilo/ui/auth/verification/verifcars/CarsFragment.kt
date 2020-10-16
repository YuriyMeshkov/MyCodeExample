package com.jobc.j112kilo.ui.auth.verification.verifcars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.AddCarFragment
import com.jobc.j112kilo.ui.auth.verification.verifcars.di.CarsComponent
import kotlinx.android.synthetic.main.cars_fragment.*
import java.util.*
import javax.inject.Inject

class CarsFragment : Fragment() {

    @Inject
    lateinit var user: UserModel
    private val viewModel: CarsViewModel by viewModels()
    private lateinit var navController: NavController
    private var adapterRv: CarsAdapterRv? = null

    @Inject
    lateinit var carsModel: CarsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CarsComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cars_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initNavController(view)
        initButtons()
        updateUI()
    }

    override fun onStart() {
        super.onStart()
        setVerificationCars()
    }

    private fun initRecyclerView() {
        rvCars.layoutManager = LinearLayoutManager(activity)
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initButtons() {
        initBtnAddCar()
        initBtnBack()
    }

    private fun initBtnAddCar() {
        btnAddCar.setOnClickListener {
            val id = Calendar.getInstance().timeInMillis.toString()
            carsModel.listCar.add(Car(carId = id))
            navController.navigate(R.id.addCarFragment, AddCarFragment.getArgument(id))
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun updateUI() {
        when(adapterRv == null) {
            true -> {
                rvCars.adapter = CarsAdapterRv(carsModel.listCar, activity) {
                    navController.navigate(R.id.addCarFragment, AddCarFragment.getArgument(it))
                }
            }
            false -> adapterRv!!.notifyDataSetChanged()
        }
    }

    private fun setVerificationCars() {
        when(carsModel.listCar.size > 0) {
            true -> user.verificationCars = true
            false -> user.verificationCars = false
        }
    }
}