package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationwithphone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.CHOICE_ROLE

import com.jobc.j112kilo.ui.auth.registrationlogin.registration.RegistrationStep2Fragment
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.RegistrationViewModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.di.RegistrationComponent
import com.jobc.j112kilo.utils.*
import kotlinx.android.synthetic.main.registration_fragment.*
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: RegistrationViewModel by viewModels {factory}
    private lateinit var navController: NavController
    private var savedStateHandle: SavedStateHandle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RegistrationComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initLiveData(view)
        initStateHandle()
        initEditText()
        initButtons(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        savedStateHandle?.remove<String>(CHOICE_ROLE)
        savedStateHandle?.remove<String>(CHOICE_COUNTRY)
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
        savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    }

    private fun initLiveData(view: View) {
        initRegistrationFormStateLiveData()
        initSendSmsLiveData(view)
        initPbLoading()
        initLoadingError(view)
    }

    private fun initRegistrationFormStateLiveData() {
        observer(viewModel.registrationFormStatePhone) { formState ->
            btnContinue.isEnabled = formState.isDataValid
            with(formState) {
                when(tvCountry.text.toString() == getText(R.string.choice_country)) {
                    true -> getColorForTextView(true, tvCountry)
                    false -> getColorForTextView(false, tvCountry)
                }
                when(tvRole.text.toString() == getText(R.string.choice_role)) {
                    true -> getColorForTextView(true, tvRole)
                    false -> getColorForTextView(false, tvRole)
                }
                phoneError?.let {
                    etUserLogin.error = it
                }
                passwordError?.let {
                    etUserPassword.error = it
                }
                passwordConfirmError?.let {
                    etUserConfirmPassword.error = it
                }
            }
        }
    }

    private fun initSendSmsLiveData(view: View) {
        observer(viewModel.sendSms) {
            when(it.status) {
                true -> {
                    navController.navigate(
                        R.id.registrationSmsFragment,
                        RegistrationStep2Fragment.newArgument(
                            when (tvRole.text.toString()) {
                                getString(R.string.sender) -> "client"
                                else -> "driver"
                            },
                            it.phone!!,
                            etUserPassword.text.toString().trim(),
                            etUserConfirmPassword.text.toString().trim(),
                            tvCountry.text.toString(),
                            it.codeSms!!
                        )
                    )
                }
                false -> {
                    if (it.info != null) {
                        Snackbar.make(view, it.info, Snackbar.LENGTH_LONG)
                            .show()
                    }
                    if (it.errors != null) {
                        Snackbar.make(view, it.errors, Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun initPbLoading() {
        observer(viewModel.loading) {
            when(it) {
                true -> {
                    pbRegistration.visibility = View.VISIBLE
                    btnContinue.isEnabled = false
                }
                false -> {
                    pbRegistration.visibility = View.GONE
                    btnContinue.isEnabled = true
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

    private fun initStateHandle() {
        initStateHandleCountry()
        initStateHandleRole()
    }

    private fun initStateHandleCountry() {
        savedStateHandle?.getLiveData<String>(CHOICE_COUNTRY)
            ?.observe(viewLifecycleOwner) { strCountry ->
                etUserLogin.isEnabled = true
                tvCountry.text = strCountry
                setHintToPhone(strCountry)
                changeDataRegistrationPhone()

                etUserLogin.afterTextPhoneChanged(strCountry) {
                    changeDataRegistrationPhone()
                    if (etUserLogin.text.toString() != it) {
                        etUserLogin.setText(it)
                        etUserLogin.setSelection(it.length)
                    }
                }
            }
    }

    private fun setHintToPhone(country: String) {
        when(country) {
            "UA" -> {
                phoneLabel.hint = resources.getString(R.string.phone_ua)
                etUserLogin.setText(R.string.phone_code_ua)
            }
            "BY" -> {
                phoneLabel.hint = resources.getString(R.string.phone_by)
                etUserLogin.setText(R.string.phone_code_by)
            }
            "RU" -> {
                phoneLabel.hint = resources.getString(R.string.phone_ru)
                etUserLogin.setText(R.string.phone_code_ru)
            }
            "KZ" -> {
                phoneLabel.hint = resources.getString(R.string.phone_kz)
                etUserLogin.setText(R.string.phone_code_kz)
            }
        }
    }

    private fun initStateHandleRole() {
        savedStateHandle?.getLiveData<String>(CHOICE_ROLE)
            ?.observe(viewLifecycleOwner) {
                tvRole.text = it
                changeDataRegistrationPhone()
            }
    }

    private fun initEditText() {
        etUserLogin.isEnabled = false

        etUserPassword.afterTextChanged {
            changeDataRegistrationPhone()
        }
        etUserConfirmPassword.afterTextChanged {
            changeDataRegistrationPhone()
        }
    }

    private fun changeDataRegistrationPhone() {
        viewModel.changeDataRegistrationPhone(
            tvCountry.text.toString(),
            tvRole.text.toString(),
            etUserLogin.text.toString(),
            etUserPassword.text.toString().trim(),
            etUserConfirmPassword.text.toString().trim()
        )
    }

    private fun initButtons(view: View) {
        initBtnChoiceCountry()
        initBtnChoiceRole()
        initBtnSignIn()
        initBtnContinue(view)
    }

    private fun initBtnChoiceCountry() {
        btnChoiceCountry.setOnClickListener {
            navController.navigate(R.id.contryFragment3)
        }
    }

    private fun initBtnChoiceRole() {
        btnChoiceRole.setOnClickListener {
            navController.navigate(R.id.choiceOfRoleFragment)
        }
    }

    private fun initBtnSignIn() {
        btnSignIn.setOnClickListener {
            navController.navigate(R.id.loginFragment) //88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
            //navController.navigate((R.id.choiceCityFromGoogleFragment4))
        }
    }

    private fun initBtnContinue(view: View) {
        btnContinue.isEnabled = false
        btnContinue.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            when {
                etUserPassword.text.toString().length < 6 -> {
                    Snackbar.make(view, getString(R.string.invalid_password), Snackbar.LENGTH_LONG)
                        .show()
                }
                etUserPassword.text.toString().trim()
                        != etUserConfirmPassword.text.toString().trim() -> {
                    Snackbar.make(view, getString(R.string.invalid_confirm_password), Snackbar.LENGTH_LONG)
                        .show()
                }
                else -> {
                    viewModel.sendSmsCode(etUserLogin.text.toString())
                }
            }

        }
    }

    private fun getColorForTextView(error: Boolean, textView: TextView) {
        activity?.let {
            when(error) {
                true -> {
                    textView.setTextColor(
                        ContextCompat.getColor(it.applicationContext, R.color.colorError)
                    )
                }
                false -> {
                    textView.setTextColor(
                        ContextCompat.getColor(it.applicationContext, R.color.colorBlack)
                    )
                }
            }
        }
    }
}