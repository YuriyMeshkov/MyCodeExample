package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.RegistrationViewModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.RegistrationAccountModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.di.RegistrationComponent
import com.jobc.j112kilo.utils.*
import kotlinx.android.synthetic.main.fragment_registration_account.*
import javax.inject.Inject

class RegistrationAccountFragment : Fragment() {

    companion object {
        private const val ROLE_FOR_REGISTRATION = "role_for_registration"
        private const val PHONE_FOR_REGISTRATION = "login_for_registration"
        private const val COUNTRY_FOR_REGISTRATION = "country_for_registration"


        fun newArgument(typeRole: String, phone: String, countryName: String) =
            Bundle().apply {
                putString(ROLE_FOR_REGISTRATION,typeRole)
                putString(PHONE_FOR_REGISTRATION, phone)
                putString(COUNTRY_FOR_REGISTRATION, countryName)
            }
    }

    @Inject
    lateinit var user: UserModel
    @Inject
    lateinit var userToken: UserTokenModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: RegistrationViewModel by viewModels {factory}
    private lateinit var navController: NavController
    private val registrationAccountModel = RegistrationAccountModel()
    private var firstDownloadFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RegistrationComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
        getDataFromArgument()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initLiveData(view)
        initButtons()
        initEditText()
        initRadioGroupChoiceGender()
        initCheckBox()
    }

    private fun getDataFromArgument() {
        arguments?.let {
            registrationAccountModel.userRole = it.getString(ROLE_FOR_REGISTRATION) ?: "client"
            registrationAccountModel.country = it.getString(COUNTRY_FOR_REGISTRATION) ?: ERROR_DATA
            registrationAccountModel.userPhone = it.getString(PHONE_FOR_REGISTRATION) ?: ERROR_DATA
        }
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initLiveData(view: View) {
        if (!firstDownloadFragment) {
            initRegistrationFormStateLiveData(view)
            initRegistrationAccount(view)
            initPbRegisteredAccount()
            initLoadingError(view)
            firstDownloadFragment = true
        }
    }

    private fun initRegistrationFormStateLiveData(view: View) {
        observer(viewModel.registrationFormStateAccount) { formState ->
            btnRegistration.isEnabled = formState.isDataValid
            with(formState) {
                usernameError?.let {
                    etUsername.error = it
                }
                userSurnameError?.let {
                    etUserSurname.error = it
                }
                userGenderError?.let {
                    Snackbar.make(view, it, Snackbar.LENGTH_SHORT)
                        .show()
                }
                emailError?.let {
                    etEmail.error = it
                }
                cityResidenceError?.let {
                    etCityOfResidence.error = it
                }
                agreementError?.let {
                    Snackbar.make(view, it, Snackbar.LENGTH_SHORT)
                        .show()
                }
                policyError?.let{
                    Snackbar.make(view, it, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun initRegistrationAccount(view: View) {
        observer(viewModel.toRegistrationAccount) {
            when(it.status) {
                true -> {
                    setDataToUser()
                    viewModel.saveToDatabaseUser(user)
                    navController.navigate(R.id.demoVersionSelectionFragment)
                }
                false -> {
                    it.info?.let { str ->
                        Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                            .show()
                    }
                    it.errors?.let {str ->
                        Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun initPbRegisteredAccount() {
        observer(viewModel.loading) {
            when (it) {
                true -> {
                    pbRegisteredAccount.visibility = View.VISIBLE
                    btnRegistration.isEnabled = false
                }
                false -> {
                    pbRegisteredAccount.visibility = View.GONE
                    btnRegistration.isEnabled = true
                }
            }
        }
    }

    private fun initLoadingError(view: View) {
        observer(viewModel.error) {
            Snackbar.make(view, getString(R.string.loading_error), Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun initEditText() {
        etUsername.afterTextChanged {
            changeDataRegistrationAccount()
        }

        etUserSurname.afterTextChanged {
            changeDataRegistrationAccount()
        }

        etEmail.afterTextChanged {
            changeDataRegistrationAccount()
        }

        etCityOfResidence.afterTextChanged {
            changeDataRegistrationAccount()
        }
    }

    private fun initRadioGroupChoiceGender() {
        rgGender.setOnCheckedChangeListener { p0, p1 ->
            val radio: RadioButton = p0.findViewById(p1)
            registrationAccountModel.userGender = radio.text.toString()
            changeDataRegistrationAccount()
        }
    }

    private fun initCheckBox() {
        cbDocumentAgreement.setOnClickListener {
            registrationAccountModel.documentApprovalAgreement = cbDocumentAgreement.isChecked
            changeDataRegistrationAccount()
        }
        cbDocumentPolicy.setOnClickListener {
            registrationAccountModel.documentApprovalPolicy = cbDocumentPolicy.isChecked
            changeDataRegistrationAccount()
        }
    }

    private fun changeDataRegistrationAccount() {
        viewModel.changeDataRegistrationAccount(
            etUsername.text.toString().trim(),
            etUserSurname.text.toString().trim(),
            registrationAccountModel.userGender,
            etEmail.text.toString().trim(),
            etCityOfResidence.text.toString().trim(),
            registrationAccountModel.documentApprovalAgreement,
            registrationAccountModel.documentApprovalPolicy
        )
    }

    private fun setDataToUser() {
        user.role = registrationAccountModel.userRole
        user.country = registrationAccountModel.country
        user.userFirstName = registrationAccountModel.userName
        user.userSurName = registrationAccountModel.userSurname
        user.userGender = registrationAccountModel.userGender
        user.cityOfResidence = registrationAccountModel.cityOfResidence
        user.documentApprovalAgreement = registrationAccountModel.documentApprovalAgreement
        user.documentApprovalPolicy = registrationAccountModel.documentApprovalPolicy
        user.userPhone = registrationAccountModel.userPhone
        user.acceptUserPhone = true
        user.userEmail = registrationAccountModel.email
    }

    private fun initButtons() {
        initBtnRegistration()
        initBtnAgreement()
        initBtnPolicy()
    }

    private fun initBtnRegistration() {
        btnRegistration.isEnabled = false
        btnRegistration.setOnClickListener {
            registrationAccountModel.userName = etUsername.text.toString().trim()
            registrationAccountModel.userSurname = etUserSurname.text.toString().trim()
            registrationAccountModel.email = etEmail.text.toString()
            registrationAccountModel.cityOfResidence = etCityOfResidence.text.toString()
            viewModel.registrationAccount(
                token =  userToken.token ?: ERROR_DATA,
                registrationAccountModel.userName,
                registrationAccountModel.userSurname,
                registrationAccountModel.userGender,
                registrationAccountModel.email,
                registrationAccountModel.cityOfResidence,
                registrationAccountModel.documentApprovalAgreement,
                registrationAccountModel.documentApprovalPolicy
            )
        }
    }

    private fun initBtnAgreement() {
        btnDocumentAgreement.setOnClickListener {
            navController.navigate(
                R.id.contentViewingFragment,
                ContentViewingFragment.newArgument(VIEWING_AGREEMENT)
            )
        }
    }

    private fun initBtnPolicy() {
        btnDocumentPolicy.setOnClickListener {
            navController.navigate(
                R.id.contentViewingFragment,
                ContentViewingFragment.newArgument(VIEWING_POLiCY)
            )
        }
    }
}