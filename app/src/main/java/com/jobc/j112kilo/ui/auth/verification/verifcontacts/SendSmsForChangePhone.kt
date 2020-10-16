package com.jobc.j112kilo.ui.auth.verification.verifcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.di.ContactsComponent
import com.jobc.j112kilo.utils.observer
import com.jobc.j112kilo.utils.removeKeyboardFromScreen
import kotlinx.android.synthetic.main.fragment_send_code_email.*
import javax.inject.Inject

class SendSmsForChangePhone : Fragment()  {

    @Inject
    lateinit var user: UserModel
    @Inject
    lateinit var userToken: UserTokenModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: ContactsDataViewModel by viewModels {factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContactsComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_code_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData(view)
        initButtons()
    }

    private fun initLiveData(view: View) {
        initConfirmedPhoneLiveData(view)
        initLoadingLiveData()
    }

    private fun initConfirmedPhoneLiveData(view: View) {
        observer(viewModel.confirmedPhone) {
            it.info?.let { str ->
                Snackbar.make(view, str, Snackbar.LENGTH_LONG)
                    .show()
            }
            if (it.status) {
                user.acceptUserPhone = true
                activity?.onBackPressed()
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
        initBtnContinue()
        initBtnCodeAgain()
    }

    private fun initBtnContinue() {
        btnContinue.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            viewModel.confirmPhone(
                user.userPhone,
                etKeyFromSMS.text.toString(),
                userToken.token!!
            )
        }
    }

    private fun initBtnCodeAgain() {
        btnCodeAgain.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            viewModel.sendSms(user.userEmail)
        }
    }

    private fun btnNoActive() {
        btnContinue.isEnabled = false
        btnCodeAgain.isEnabled = false
    }

    private fun btnActive() {
        btnContinue.isEnabled = true
        btnCodeAgain.isEnabled = true
    }
}