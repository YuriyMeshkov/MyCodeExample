package com.jobc.j112kilo.ui.auth.verification

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.verification.data.model.VerificationFormState
import com.jobc.j112kilo.ui.auth.verification.di.VerificationComponent
import com.jobc.j112kilo.ui.main.MainActivity
import com.jobc.j112kilo.utils.observer
import kotlinx.android.synthetic.main.verification_fragment.*
import javax.inject.Inject

class VerificationFragment : Fragment() {

    @Inject
    lateinit var user: UserModel
    @Inject
    lateinit var userToken: UserTokenModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: VerificationViewModel by viewModels {factory}
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VerificationComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.verification_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initLiveData()
        initButtons()
        initVerificationSettings()
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initLiveData() {
        initChangeVerificationFormState()
    }

    private fun initChangeVerificationFormState() {
        observer(viewModel.verificationFormState) {
            btnAccept.isEnabled = it.isDataValid
        }
    }

    private fun initButtons() {
        initBtnEnterContacts()
        initBtnPersonalData()
        initBtnEnterLicense()
        initBtnEnterCars()
        initVerificationAccept()
    }

    private fun initBtnEnterContacts() {
        btnEnterContacts.setOnClickListener {
            navController.navigate(R.id.contactsDataFragment)
        }
    }

    private fun initBtnPersonalData() {
        btnEnterPersonalData.setOnClickListener {
            navController.navigate(R.id.personalDataFragment)
        }
    }

    private fun initBtnEnterLicense() {
        btnEnterLicence.setOnClickListener {
            navController.navigate((R.id.drivingLicenseFragment))
        }
    }

    private fun initBtnEnterCars() {
        btnEnterCars.setOnClickListener {
            navController.navigate(R.id.carsFragment)
        }
    }

    private fun initVerificationAccept() {
        btnAccept.isEnabled = false
        btnAccept.setOnClickListener {
            userToken.verified = true
            viewModel.sendingUserDataToServer(user, userToken)
            startMainActivity()
        }
    }

    private fun initVerificationSettings() {
        if (user.verificationContacts) {
            ivChoiceContactsActive.visibility = View.VISIBLE
        } else {
            ivChoiceContactsActive.visibility = View.GONE
        }
        if (user.verificationPersonalData) {
            ivChoiceDataActive.visibility = View.VISIBLE
        } else {
            ivChoiceDataActive.visibility = View.GONE
        }
        if (user.verificationLicense) {
            ivChoiceLicenceActive.visibility = View.VISIBLE
        } else {
            ivChoiceLicenceActive.visibility = View.GONE
        }
        if (user.verificationCars) {
            ivChoiceCarsActive.visibility = View.VISIBLE
        } else {
            ivChoiceCarsActive.visibility = View.GONE
        }
        changeVerificationFormState()
    }

    private fun changeVerificationFormState() {
        viewModel.changeVerificationFormState(
            VerificationFormState(
                contactsDataError = user.verificationContacts,
                personalDataError = user.verificationPersonalData,
                drivingLicenseError = user.verificationLicense,
                carsError = user.verificationCars
            )
        )
    }

    private fun startMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}