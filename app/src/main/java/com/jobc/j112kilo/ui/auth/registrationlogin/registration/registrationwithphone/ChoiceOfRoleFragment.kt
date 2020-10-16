package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationwithphone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.CHOICE_ROLE
import kotlinx.android.synthetic.main.fragment_choice_of_role.*

class ChoiceOfRoleFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_of_role, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initButtons()
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initButtons() {
        initBtnSender()
        initBtnCarrier()
        initBtnBack()
    }

    private fun initBtnSender() {
        btnSender.setOnClickListener {
            navController.previousBackStackEntry?.savedStateHandle
                ?.set(CHOICE_ROLE, resources.getString(R.string.sender))
            activity?.onBackPressed()
        }
    }
    private fun initBtnCarrier() {
        btnCarrier.setOnClickListener {
            navController.previousBackStackEntry?.savedStateHandle
                ?.set(CHOICE_ROLE, resources.getString(R.string.carrier))
            activity?.onBackPressed()
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}