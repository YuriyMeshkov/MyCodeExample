package com.jobc.j112kilo.ui.auth.verification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jobc.j112kilo.R

class VerificationActivity : AppCompatActivity(R.layout.verification_activity) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(
            this,
            R.id.my_nav_host_verification_fragment
        )
    }

}