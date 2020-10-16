package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense

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
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.imgtransformation.ImgTransformationActivity
import com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense.di.LicenseComponent
import com.jobc.j112kilo.utils.*
import kotlinx.android.synthetic.main.driving_license_fragment.*
import javax.inject.Inject

class DrivingLicenseFragment : Fragment() {

    companion object {
        fun newInstance() = DrivingLicenseFragment()
    }

    @Inject
    lateinit var user: UserModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: DrivingLicenseViewModel by viewModels {factory}
    private lateinit var navController: NavController
    private var savedStateHandle: SavedStateHandle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LicenseComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.driving_license_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initLiveData()
        initStateHandle()
        initEditText()
        initButtons()
    }

    override fun onStart() {
        super.onStart()
        loadImages()
        //loadingPhotosForLicense()
    }

    override fun onDestroy() {
        super.onDestroy()
        savedStateHandle?.remove<String>(CALENDAR_DATA)
    }


    private fun initNavController(view: View) {
        navController = view.findNavController()
        savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    }

    private fun initLiveData() {
        initLicenseFormState()
        initFirstImageLoadedLiveData()
        initSecondImageLoadedLiveData()
    }

    private fun initLicenseFormState() {
        observer(viewModel.licenseFormState) { formState ->
            btnAccept.isEnabled = formState.isDataValid
            with(formState) {
                issuedByError?.let {
                    etIssuedBy.error = it
                }
                dataError?.let{
                    //etIssuedData.error = it
                }
                seriesError?.let{
                    etSeries.error = it
                }
                numberError?.let{
                    etNumber.error = it
                }
            }
        }
    }

    private fun initFirstImageLoadedLiveData() {
        observer(viewModel.loadImgFirst) {
            user.photoLicenseFirst = it
            user.pathToPhotoFirstLicense = getUrl(IMG_DRIVING_LICENSE_SECOND)
            loadingPhotosForLicense()
            changeDataLicense()
        }
    }

    private fun initSecondImageLoadedLiveData() {
        observer(viewModel.loadImgSecond) {
            user.photoLicenseSecond = it
            user.pathToPhotoSecondLicense = getUrl(IMG_DRIVING_LICENSE_SECOND)
            loadingPhotosForLicense()
            changeDataLicense()
        }
    }

    private fun initStateHandle() {
        savedStateHandle?.getLiveData<String>(CALENDAR_DATA)
            ?.observe(viewLifecycleOwner) {
                etIssuedData.setText(it)
                changeDataLicense()
            }
    }

    private fun initEditText() {
        etIssuedBy.afterTextChanged { changeDataLicense() }
        etIssuedData.afterTextDateChange {
            issuedDataLabel.hint = getText(R.string.date_hint)
            if (etIssuedData.text.toString() != it) {
                etIssuedData.setText(it)
                etIssuedData.setSelection(it.length)
            }
            changeDataLicense()
        }
        etSeries.afterTextChanged { changeDataLicense() }
        etNumber.afterTextChanged { changeDataLicense() }
    }

    private fun changeDataLicense() {
        viewModel.changeDataLicense(
            issuedBy = etIssuedBy.text.toString().trim(),
            data = etIssuedData.text.toString().trim(),
            series = etSeries.text.toString().trim(),
            number = etNumber.text.toString().trim(),
            photoFirst = user.pathToPhotoFirstLicense,
            photoSecond = user.pathToPhotoSecondLicense
        )

    }

    private fun initButtons() {
        initBtnPhotoLicenseFirst()
        initBtnPhotoLicenseSecond()
        initBtnCalendar()
        initBtnAccept()
        initBtnBack()
    }

    private fun loadingPhotosForLicense() {
        if (user.pathToPhotoFirstLicense.isNotEmpty()) {
            ivPhotoLicenseFirst.setImageBitmap(user.photoLicenseFirst)
        }
        if (user.pathToPhotoSecondLicense.isNotEmpty()) {
            ivPhotoLicenseSecond.setImageBitmap(user.photoLicenseSecond)
        }
    }

    private fun initBtnPhotoLicenseFirst() {
        btnPhotoFirst.setOnClickListener {
            activity?.let {
                val intent = ImgTransformationActivity.newIntent(
                    it.applicationContext,
                    getPathFile(),
                    IMG_DRIVING_LICENSE_FIRST,
                    LAYOUT_FOR_DRIVER_LICENSE
                )
                startActivity(intent)
            }
        }
    }

    private fun initBtnPhotoLicenseSecond() {
        btnPhotoSecond.setOnClickListener {
            activity?.let {
                val intent = ImgTransformationActivity.newIntent(
                    it.applicationContext,
                    getPathFile(),
                    IMG_DRIVING_LICENSE_SECOND,
                    LAYOUT_FOR_DRIVER_LICENSE
                )
                startActivity(intent)
            }
        }
    }

    private fun initBtnCalendar() {
        btnCalendar.setOnClickListener {
            navController.navigate(R.id.calendarFragment)
        }
    }

    private fun initBtnAccept() {
        btnAccept.isEnabled = false
        btnAccept.setOnClickListener {
            user.licenseIssuedBy = etIssuedBy.text.toString().trim()
            user.licenseDateOfIssue = etIssuedData.text.toString().trim()
            user.licenseSeries = etSeries.text.toString().trim()
            user.licenseNumber = etNumber.text.toString().trim()
            user.verificationLicense = true
            activity?.onBackPressed()
        }
    }

    private fun loadImages() {
        viewModel.loadingImage(getUrl(IMG_DRIVING_LICENSE_FIRST))
        viewModel.loadingImage(getUrl(IMG_DRIVING_LICENSE_SECOND))
    }

    private fun getUrl(nameImg: String) =
        StringBuilder()
            .append(getPathFile())
            .append(nameImg)
            .toString()

    private fun getPathFile() =
        StringBuilder()
            .append(activity?.filesDir.toString())
            .append(PATH_IMAGES_DRIVER_LICENSE)
            .toString()

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}