package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.auth.verification.verifcars.data.model.CarType
import com.jobc.j112kilo.ui.auth.verification.verifcars.data.model.CarsType
import kotlinx.android.synthetic.main.fragment_type_cars.*

class TypeCarsFragment : Fragment() {

    private lateinit var carsType: List<CarType>
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type_cars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataCarsType()
        initNavController(view)
        initButtons()
    }

    private fun getDataCarsType() {
        activity?.let {
            carsType = CarsType().getCarsType(it.resources)
        }
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initButtons() {
        initRadioGroupCarType()
        initBtnAccept()
        initBtnBack()
    }

    private fun initRadioGroupCarType() {
        rgCarType.setOnCheckedChangeListener { p0, p1 ->
            val radio: RadioButton = p0.findViewById(p1)
            navController.previousBackStackEntry?.savedStateHandle?.set("key", radio.text.toString())
            isActiveBtnAccept(true)
        }
    }

    private fun initBtnAccept() {
        isActiveBtnAccept(false)
        btnAcceptCarType.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun isActiveBtnAccept(state: Boolean) {
        btnAcceptCarType.isEnabled = state
    }

}