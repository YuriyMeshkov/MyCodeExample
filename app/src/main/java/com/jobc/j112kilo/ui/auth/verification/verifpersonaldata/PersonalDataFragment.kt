package com.jobc.j112kilo.ui.auth.verification.verifpersonaldata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.imgtransformation.ImgTransformationActivity
import com.jobc.j112kilo.ui.auth.verification.verifpersonaldata.di.PersonalComponent
import com.jobc.j112kilo.utils.IMG_AVATAR
import com.jobc.j112kilo.utils.LAYOUT_FOR_AVATAR
import com.jobc.j112kilo.utils.PATH_IMAGES_AVATAR
import com.jobc.j112kilo.utils.observer
import kotlinx.android.synthetic.main.personal_data_fragment.*
import javax.inject.Inject

class PersonalDataFragment : Fragment() {

    companion object {
        fun newInstance() = PersonalDataFragment()
    }

    @Inject
    lateinit var user:UserModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: PersonalDataViewModel by viewModels { factory }
    private var gender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PersonalComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_data_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPersonalDataSetting()
        initLiveData()
        initRadioGroupChoiceGender()
        initButtons()
    }

    override fun onStart() {
        super.onStart()
        loadImage()
        //loadingAvatar()
    }

    private fun initPersonalDataSetting() {
        etUsername.setText(user.userFirstName)
        etUserSurname.setText(user.userSurName)
        etCityOfResidence.setText((user.cityOfResidence))
        if(user.userGender == "male") {
            rbMale.isChecked = true
        }
        if (user.userGender == "female") {
            rbFemale.isChecked = true
        }
    }

    private fun initLiveData() {
        initImageLoadedLiveData()
    }

    private fun initImageLoadedLiveData() {
        observer(viewModel.loadImg) {
            user.avatar = it
            user.pathToAvatar = getUrl()
            loadingAvatar()
            //ivAvatar.setImageBitmap(it)
            user.avatar = it
        }
    }

    private fun initButtons() {
        initBtnAddAvatar()
        initBtnAccept()
        initBtnBack()
    }

    private fun loadingAvatar() {
        if (user.pathToAvatar.isNotEmpty()){
            ivAvatar.setImageBitmap(user.avatar)
        }
    }

    private fun initBtnAddAvatar() {
        btnAddAvatar.setOnClickListener {
            activity?.let {
                val intent = ImgTransformationActivity.newIntent(
                    it.applicationContext,
                    getPathFile(),
                    IMG_AVATAR,
                    LAYOUT_FOR_AVATAR
                )
                startActivity(intent)
            }
        }
    }

    private fun initRadioGroupChoiceGender() {
        rgGender.setOnCheckedChangeListener { p0, p1 ->
            val radio: RadioButton = p0.findViewById(p1)
            gender = radio.text.toString()
        }
    }

    private fun initBtnAccept() {
        btnAcceptPersonalData.setOnClickListener {
            user.userFirstName = etUsername.text.toString().trim()
            user.userSurName = etUserSurname.text.toString().trim()
            user.userFirstName = etCityOfResidence.text.toString()
            gender?.let {
                user.userGender = it
            }
            user.verificationPersonalData = true
            activity?.onBackPressed()
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun loadImage() {
        viewModel.loadingImage(getUrl())
    }

    private fun getUrl() =
        StringBuilder()
            .append(getPathFile())
            .append(IMG_AVATAR)
            .toString()

    private fun getPathFile() =
        StringBuilder()
            .append(activity?.filesDir.toString())
            .append(PATH_IMAGES_AVATAR)
            .toString()


}