package com.jobc.j112kilo.ui.auth.registrationlogin.registration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.main.MainActivity
import com.jobc.j112kilo.ui.auth.verification.VerificationActivity
import kotlinx.android.synthetic.main.fragment_demo_version_selection.*

class DemoVersionSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_demo_version_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        initBtnDemo()
        initBtnVerification()
    }

    private fun initBtnDemo() {
        btnDemo.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun initBtnVerification() {
        btnVerification.setOnClickListener {
            val intent = Intent(activity, VerificationActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}