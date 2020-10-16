package com.jobc.j112kilo.ui.auth.verification.verifcontacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationwithphone.CHOICE_COUNTRY
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.di.ContactsComponent
import com.jobc.j112kilo.utils.afterTextPhoneChanged
import com.jobc.j112kilo.utils.observer
import com.jobc.j112kilo.utils.removeKeyboardFromScreen
import kotlinx.android.synthetic.main.contacts_data_fragment.*

import javax.inject.Inject


class ContactsDataFragment : Fragment() {

    @Inject
    lateinit var user: UserModel
    @Inject
    lateinit var userToken: UserTokenModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: ContactsDataViewModel by viewModels {factory}
    private lateinit var navController: NavController
    private var savedStateHandle: SavedStateHandle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContactsComponent.init((requireActivity().application as App).appComponent)
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contacts_data_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initContactsSetting()
        initStateHandle()
        initLiveData(view)
        initButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        savedStateHandle?.remove<String>(CHOICE_COUNTRY)
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
        savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    }

    private fun initContactsSetting() {
        etUserLogin.setText(user.userPhone)
        when(user.acceptUserPhone) {
            true -> etUserLogin.isEnabled = false
            false -> etUserLogin.isEnabled = true
        }
        when (user.acceptUserEmail) {
            true -> etUserEmail.isEnabled = false
            false -> etUserEmail.isEnabled = true
        }
        etUserEmail.setText(user.userEmail)
        setBtnChangeOrConfirm()
        btnAcceptActive()
    }

    private fun initLiveData(view: View) {
        initSmsCodeLiveData(view)
        initEmailCodeLiveData(view)
        initLoadingLiveData()
    }

    private fun initSmsCodeLiveData(view: View) {
        observer(viewModel.smsCode) {
            it.info?.let { str ->
                Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                    .show()
            }

            when(it.status) {
                true -> {
                    user.userPhone = etUserLogin.text.toString()
                    navController.navigate(R.id.sendSmsForChangePhone)
                }
                false -> {
                    Snackbar.make(view, getText(R.string.email_use), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initEmailCodeLiveData(view: View) {
        observer(viewModel.emailCode) {
            it.info?.let { str ->
                Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                    .show()
            }
            when(it.status) {
                true -> navController.navigate(R.id.sendCodeEmailFragment)
                false -> {
                    Snackbar.make(view, getText(R.string.email_use), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initLoadingLiveData() {
        observer(viewModel.loading) {
            when(it) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                    btnNoActive()
                }
                false -> {
                    progressBar.visibility = View.GONE
                    btnActive()
                }
            }
        }
    }

    private fun initButtons() {
        initBtnChoiceCountry()
        initBtnChangePhone()
        initBtnConfirmPhone()
        initBtnConfirmEmail()
        initBtnChangeEmail()
        initBtnAccept()
        initBtnBack()
    }

    private fun initBtnChoiceCountry() {
        btnChoiceCountry.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            navController.navigate(R.id.countryFragment)
        }
    }

    private fun initBtnChangePhone() {
        btnChangePhone.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            user.acceptUserPhone = false
            etUserLogin.setText("")
            setBtnChangeOrConfirm()
        }
    }

    private fun initBtnConfirmPhone() {
        btnConfirmPhone.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            viewModel.sendSms(etUserLogin.text.toString())
        }
    }

    private fun initBtnConfirmEmail() {
        btnConfirmEmail.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            user.userEmail = etUserEmail.text.toString()
            viewModel.sendEmail(etUserEmail.text.toString(), userToken.token!!)
        }
    }

    private fun initBtnChangeEmail() {
        btnChangeEmail.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            user.acceptUserEmail = false
            initContactsSetting()
        }
    }

    private fun initBtnAccept() {
        btnAcceptActive()
        btnAcceptContacts.setOnClickListener {
            user.verificationContacts = true
            activity?.onBackPressed()
        }
    }

    private fun setBtnChangeOrConfirm() {
        when(user.acceptUserPhone) {
            true -> {
                btnChangePhone.visibility = View.VISIBLE
                btnChoiceCountry.visibility = View.GONE
            }
            false -> {
                btnChangePhone.visibility = View.GONE
                btnChoiceCountry.visibility = View.VISIBLE
            }
        }
        when(user.acceptUserEmail) {
            true -> btnChangeEmail.visibility = View.VISIBLE
            false -> btnChangeEmail.visibility = View.GONE
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initStateHandle() {
        savedStateHandle?.getLiveData<String>(CHOICE_COUNTRY)
            ?.observe(viewLifecycleOwner) { strCountry ->
                etUserLogin.isEnabled = true
                tvCountry.text = strCountry
                setHintToPhone(strCountry)
                etUserLogin.afterTextPhoneChanged(strCountry) {
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

    private fun btnNoActive() {
        btnConfirmPhone.isEnabled = false
        btnChangePhone.isEnabled = false
        btnConfirmEmail.isEnabled = false
        btnChangeEmail.isEnabled = false
        btnChoiceCountry.isEnabled = false
    }

    private fun btnActive() {
        btnConfirmPhone.isEnabled = true
        btnChangePhone.isEnabled = true
        btnConfirmEmail.isEnabled = true
        btnChangeEmail.isEnabled = true
        btnChoiceCountry.isEnabled = true
    }

    private fun btnAcceptActive() {
        when (user.acceptUserEmail && user.acceptUserPhone) {
            true -> btnAcceptContacts.isEnabled = true
            false -> btnAcceptContacts.isEnabled = false
        }
    }
}