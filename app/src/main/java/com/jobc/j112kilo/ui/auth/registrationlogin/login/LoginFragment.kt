package com.jobc.j112kilo.ui.auth.registrationlogin.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserTokenModel
import com.jobc.j112kilo.ui.auth.registrationlogin.login.di.LoginComponent
import com.jobc.j112kilo.ui.auth.registrationlogin.registration.data.model.UserTokenResponseModel
import com.jobc.j112kilo.ui.main.MainActivity
import com.jobc.j112kilo.utils.afterTextChanged
import com.jobc.j112kilo.utils.observer
import com.jobc.j112kilo.utils.removeKeyboardFromScreen
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var userToken: UserTokenModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { factory }
    private var firstDownloadFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData(view)
        initEditText()
        initButtons()
    }

    private fun initLiveData(view: View) {
        if (!firstDownloadFragment) {
            initLoginFormStateLiveData()
            initGetUserToken()
            initPbLogin()
            initLoadingError(view)
            firstDownloadFragment = true
        }
    }

    private fun initLoginFormStateLiveData() {
        observer(viewModel.loginFormState) { loginFormState ->
            btnLogin.isEnabled = loginFormState.isDataValid
            with(loginFormState) {
                loginError?.let {
                    etUserLogin.error = it
                }
                passwordError?.let {
                    etUserPassword.error = it
                }
            }
        }
    }

    private fun initGetUserToken() {
        observer(viewModel.userTokenResponse) {
            setDataToUserToken(it)
            val intent = Intent(activity, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun initPbLogin() {
        observer(viewModel.loading) {
            when(it) {
                true -> pbLogin.visibility = View.VISIBLE
                false -> pbLogin.visibility =View.GONE
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
        etUserLogin.afterTextChanged {
            changeLogin()
        }
        etUserPassword.afterTextChanged {
            changeLogin()
        }
    }

    private fun changeLogin() {
        viewModel.changeLogin(
            etUserLogin.text.toString(),
            etUserPassword.text.toString()
        )
    }

    private fun initButtons() {
        initBtnLogin()
        initBtnBack()
    }

    private fun initBtnLogin() {
        btnLogin.isEnabled = false
        btnLogin.setOnClickListener {
            removeKeyboardFromScreen(it, activity)
            viewModel.loginUsedToken(
                etUserLogin.text.toString().trim(),
                etUserPassword.text.toString().trim()
            )
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
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