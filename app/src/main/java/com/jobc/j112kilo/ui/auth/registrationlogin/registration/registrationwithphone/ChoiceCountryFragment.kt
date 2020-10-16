package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationwithphone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jobc.j112kilo.R
import kotlinx.android.synthetic.main.fragment_choice_country.*


const val CHOICE_COUNTRY = "choice_country"

class CountryFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initButton()
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initButton() {
        initRadioGroupChoiceCountry()
        initBtnAccept()
        initBtnBack()
    }

    private fun initRadioGroupChoiceCountry() {
        rgChoiceCountry.setOnCheckedChangeListener { p0, p1 ->
            var country: String? = null
            val radio: RadioButton = p0.findViewById(p1)
            when(radio.text.toString()) {
                getString(R.string.country_ukraine) -> country = "UA"
                getString(R.string.country_russian) -> country = "RU"
                getString(R.string.country_belarus) -> country = "BY"
                getString(R.string.country_kazakhstan) -> country = "KZ"
            }
            navController.previousBackStackEntry?.savedStateHandle?.set(CHOICE_COUNTRY, country)
            btnAccept.isEnabled = true
        }
    }

    private fun initBtnAccept() {
        btnAccept.isEnabled = false
        btnAccept.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}