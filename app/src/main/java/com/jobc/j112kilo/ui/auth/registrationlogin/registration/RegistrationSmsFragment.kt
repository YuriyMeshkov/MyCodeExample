package com.jobc.j112kilo.ui.auth.registrationlogin.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.RegistrationModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.di.RegistrationComponent
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount.RegistrationAccountFragment
import com.jobc.j112kilo.utils.*
import kotlinx.android.synthetic.main.fragment_registration_sms.*
import javax.inject.Inject

const val CHOICE_ROLE = "choice_role"

class RegistrationStep2Fragment : Fragment() {

    companion object {
        private const val ROLE_FOR_REGISTRATION = "role_for_registration"
        private const val PHONE_FOR_REGISTRATION = "login_for_registration"
        private const val PASSWORD_FOR_REGISTRATION = "password_for_registration"
        private const val PASSWORD_CONFIRM_REGISTRATION = "password_confirm_registration"
        private const val COUNTRY_FOR_REGISTRATION = "country_for_registration"
        private const val CODE_SMS_FOR_REGISTRATION = "code_sms_for_registration"


        fun newArgument(typeRole: String, phone: String, password: String,
                        passwordConfirm: String, countryName: String, codeSms: String) =
            Bundle().apply {
                putString(ROLE_FOR_REGISTRATION,typeRole)
                putString(PHONE_FOR_REGISTRATION, phone)
                putString(PASSWORD_FOR_REGISTRATION, password)
                putString(PASSWORD_CONFIRM_REGISTRATION, passwordConfirm)
                putString(COUNTRY_FOR_REGISTRATION, countryName)
                putString(CODE_SMS_FOR_REGISTRATION, codeSms)
        }
    }

    @Inject
    lateinit var userToken: UserTokenModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: RegistrationViewModel by viewModels {factory}
    private lateinit var navController: NavController
    private lateinit var registrationModel: RegistrationModel

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
        return inflater.inflate(R.layout.fragment_registration_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initEditText()
        initLiveData(view)
        initButtons()
    }

    private fun getDataFromArgument() {
        arguments?.let {
            registrationModel = RegistrationModel(
                it.getString(ROLE_FOR_REGISTRATION) ?: ERROR_DATA,
                it.getString(PASSWORD_FOR_REGISTRATION) ?: ERROR_DATA,
                it.getString(PASSWORD_CONFIRM_REGISTRATION) ?: ERROR_DATA,
                phoneNumberStringBuild(it.getString(PHONE_FOR_REGISTRATION)!!),
                it.getString(COUNTRY_FOR_REGISTRATION) ?: ERROR_DATA,
                it.getString(CODE_SMS_FOR_REGISTRATION)?: ERROR_DATA
            )
        }
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initEditText() {
        etKeyFromSMS.afterTextChanged {
            if (etKeyFromSMS.text.toString() == registrationModel.smsCode) {
                btnContinue.isEnabled = true
            }
        }
    }

    private fun initLiveData(view: View) {
        initRegisteredLiveData(view)
        initGetUserTokenLiveData()
        initPbLoadingLiveData()
        initLoadingError(view)
    }

    private fun initRegisteredLiveData(view: View) {
       observer(viewModel.registered) {
           when(it.success) {
               true -> {
                   viewModel.loginUsedToken(registrationModel.phone, registrationModel.password)
               }
               false -> {
                   if (it.errors != null) {
                       Snackbar.make(view, it.errors, Snackbar.LENGTH_LONG)
                           .show()
                   }
               }
           }
       }
   }

    private fun initGetUserTokenLiveData() {
        observer(viewModel.userTokenResponse) {
            when(it.token != null) {
                true -> {
                    setDataToUserToken(it)
                    navController.navigate(
                        R.id.registrationAccountFragment,
                        RegistrationAccountFragment.newArgument(
                            registrationModel.type,
                            registrationModel.phone,
                            registrationModel.countryName
                        )
                    )
                }
                false -> {
                    //обработать ошибки
                }
            }
        }
    }

    private fun initPbLoadingLiveData() {
        observer(viewModel.loading) {
            when(it) {
                true -> {
                    pbLoading.visibility = View.VISIBLE
                    btnContinue.isEnabled = false
                    btnCodeAgain.isEnabled = false
                }
                false -> {
                    pbLoading.visibility = View.GONE
                    btnContinue.isEnabled = true
                    btnCodeAgain.isEnabled = true
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

    private fun initButtons() {
        initBtnContinue()
        initBtnCodeAgain()
    }

    private fun initBtnContinue() {
        btnContinue.isEnabled = false
        btnContinue.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            viewModel.registrationUser(registrationModel)
        }
    }

    private fun initBtnCodeAgain() {
        btnCodeAgain.isEnabled = false
        btnCodeAgain.setOnClickListener {
            viewModel.sendSmsCode(registrationModel.phone)
        }
    }

    private fun setDataToUserToken(data: UserTokenResponseModel) {
        userToken.token = data.token
        userToken.verified = data.verified
        userToken.userId = data.userId
        userToken.role = data.role
        userToken.deviceId = data.deviceId
    }
}