package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.choicecityfromgoogle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.jobc.j112kilo.R
import javax.inject.Inject

class CityChoiceFromGoogleFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CityChoiceFromGoogleViewModel by viewModels {factory}
    private lateinit var navController: NavController
    private var choiceAdapterRv: CityChoiceAdapterRv? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_choice_city_from_google,
            container,
            false
        )
    }

}