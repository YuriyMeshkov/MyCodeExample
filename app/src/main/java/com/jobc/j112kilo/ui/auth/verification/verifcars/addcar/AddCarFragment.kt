package com.jobc.j112kilo.ui.auth.verification.verifcars.addcar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.Car
import com.jobc.j112kilo.data.model.CarsModel
import com.jobc.j112kilo.ui.imgtransformation.ImgTransformationActivity
import com.jobc.j112kilo.ui.auth.verification.verifcars.addcar.di.AddCarComponent
import com.jobc.j112kilo.utils.*
import kotlinx.android.synthetic.main.add_car_fragment.*
import javax.inject.Inject

class AddCarFragment : Fragment() {

    companion object {
        private const val ID_CAR = "id_car"
        fun getArgument(id: String) = bundleOf(ID_CAR to id)
    }

    @Inject
    lateinit var carsModel: CarsModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: AddCarViewModel by viewModels {factory}
    private lateinit var car: Car
    private lateinit var navController: NavController
    private var savedStateHandle: SavedStateHandle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AddCarComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCarData()
        initNavController(view)
        initLiveData(view)
        initButtons()
        initEditText()
        overrideCallBackPressed()
    }

    private fun overrideCallBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                deleteCarData()
                navController.popBackStack(R.id.carsFragment, false)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onStart() {
        super.onStart()
        loadImages()
        //loadingPhotosForCar()
    }

    override fun onDestroy() {
        super.onDestroy()
        savedStateHandle?.remove<String>("key")
    }

    private fun getCarData() {
        arguments?.let { arg ->
            val id = arg.getString(ID_CAR)
            carsModel.listCar.forEach { if(it.carId == id) { car = it } }
            if (car.carVerification) {
                loadDataCar()
            }
        }
    }
    private fun loadDataCar() {
        tvCarType.text = car.carType
        etCarModel.setText(car.carModel)
        etRegistrationNumber.setText(car.carRegistrationNum)
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
        savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<String>("key")
            ?.observe(viewLifecycleOwner) {
                tvCarType.text = it
                changeDataCar()
        }
    }

    private fun initLiveData(view: View) {
        initFormStateLiveData(view)
        initCarPhotoLiveData()
        initFirstPhotoPassportLiveData()
        initSecondPhotoPassportLiveData()
    }

    private fun initFormStateLiveData(view: View) {
        observer(viewModel.carFormState) { carFromState ->
            btnAccept.isEnabled = carFromState.isDataValid
            with(carFromState) {
                when(carTypeError != null) {
                    true -> { getColorForTextView(true) }
                    false -> { getColorForTextView(false) }
                }
                carModelError?.let {
                    etCarModel.error = it
                }
                carRegistrationNumError?.let {
                    etRegistrationNumber.error = it
                }
                if (car.pathToCarPhoto == null) {
                    Snackbar.make(
                        view,
                        getString(R.string.invalid_load_photo_car),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                if (car.pathToPassportPhotoFirst == null) {
                    Snackbar.make(
                        view,
                        getString(R.string.invalid_load_photo_tehnical_pasport),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initCarPhotoLiveData() {
        observer(viewModel.loadPhotoCar) {
            car.carPhoto = it
            car.pathToCarPhoto = getUrl(IMG_CAR)
            loadingPhotosForCar()
            //ivPhotoCar.setImageBitmap(car.carPhoto)
            car.carPhotoChange = true
            changeDataCar()
        }
    }

    private fun initFirstPhotoPassportLiveData() {
        observer(viewModel.loadFirstPhotoPassport) {
            car.photoPassportFirst = it
            car.pathToPassportPhotoFirst = getUrl(IMG_TECHNICAL_PASSPORT_FIRS)
            loadingPhotosForCar()
            //ivPhotoPassportCarFirst.setImageBitmap(car.photoPassportFirst)
            car.carPassportChange = true
            changeDataCar()
        }
    }

    private fun initSecondPhotoPassportLiveData() {
        observer(viewModel.loadSecondPhotoPassport) {
            car.photoPassportSecond = it
            car.pathToPassportPhotoSecond = getUrl(IMG_TECHNICAL_PASSPORT_SECOND)
            loadingPhotosForCar()
            //ivPhotoPassportCarSecond.setImageBitmap(it)
            car.carPassportChange = true
            changeDataCar()
        }
    }

    private fun getColorForTextView(error: Boolean) {
        activity?.let {
            when(error) {
                true -> {
                    tvCarType.setTextColor(
                        ContextCompat.getColor(it.applicationContext, R.color.colorError)
                    )
                }
                false -> {
                    tvCarType.setTextColor(
                        ContextCompat.getColor(it.applicationContext, R.color.colorBlack)
                    )
                }
            }
        }
    }

    private fun initButtons() {
        initBtnChoiceCarType()
        initBtnPhotoCar()
        initBtnPhotoFirstPassport()
        initBtnPhotoSecondPassport()
        initBtnAccept()
        initBtnBack()
    }

    private fun initBtnChoiceCarType() {
        btnChoiceCarType.setOnClickListener {
            navController.navigate(R.id.typeCarsFragment)
        }
    }

    private fun initBtnPhotoCar() {
        btnAddCarPhoto.setOnClickListener {
            activity?.let {
                val intent = ImgTransformationActivity.newIntent(
                    it.applicationContext,
                    getPathFile(),
                    IMG_CAR,
                    LAYOUT_FOR_DRIVER_LICENSE
                )
                startActivity(intent)
            }
        }
    }

    private fun initBtnPhotoFirstPassport() {
        btnPhotoPassportCarFirst.setOnClickListener {
            activity?.let {
                val intent = ImgTransformationActivity.newIntent(
                    it.applicationContext,
                    getPathFile(),
                    IMG_TECHNICAL_PASSPORT_FIRS,
                    LAYOUT_FOR_DRIVER_LICENSE
                )
                startActivity(intent)
            }
        }
    }

    private fun initBtnPhotoSecondPassport() {
        btnPhotoPassportCarSecond.setOnClickListener {
            activity?.let {
                val intent = ImgTransformationActivity.newIntent(
                    it.applicationContext,
                    getPathFile(),
                    IMG_TECHNICAL_PASSPORT_SECOND,
                    LAYOUT_FOR_DRIVER_LICENSE
                )
                startActivity(intent)
            }
        }
    }

    private fun initBtnAccept() {
        btnAccept.isEnabled = false
        btnAccept.setOnClickListener {
            setCarData()
            car.carVerification = true
            viewModel.saveCarToDatabase(car)
            activity?.onBackPressed()
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initEditText() {
        etCarModel.afterTextChanged {
            changeDataCar()
        }
        etRegistrationNumber.afterTextChanged {
            changeDataCar()
        }
    }

    private fun changeDataCar() {
        viewModel.changeDataCar(
            tvCarType.text.toString(),
            etCarModel.text.toString(),
            etRegistrationNumber.text.toString(),
            car.carPhotoChange,
            car.carPassportChange
        )
    }

    private fun loadImages() {
        viewModel.loadingImage(getUrl(IMG_CAR))
        viewModel.loadingImage(getUrl(IMG_TECHNICAL_PASSPORT_FIRS))
        viewModel.loadingImage(getUrl(IMG_TECHNICAL_PASSPORT_SECOND))
    }

    private fun loadingPhotosForCar() {
        car.pathToCarPhoto?.let {
            ivPhotoCar.setImageBitmap(car.carPhoto)
        }
        car.pathToPassportPhotoFirst?.let {
            ivPhotoPassportCarFirst.setImageBitmap(car.photoPassportFirst)
        }
        car.pathToPassportPhotoFirst?.let {
            ivPhotoPassportCarSecond.setImageBitmap(car.photoPassportSecond)
        }

    }

    private fun getUrl(nameImg: String) =
        StringBuilder()
            .append(getPathFile())
            .append(nameImg)
            .toString()

    private fun getPathFile() =
        StringBuilder()
            .append(activity?.filesDir.toString())
            .append(PATH_IMAGES_CARS)
            .append(car.carId)
            .append("/")
            .toString()

    private fun setCarData() {
        car.carType = tvCarType.text.toString()
        car.carModel = etCarModel.text.toString()
        car.carRegistrationNum = etRegistrationNumber.text.toString()
    }

    private fun deleteCarData() {
        if (!car.carVerification) {
            car.carPhoto = null
            car.photoPassportFirst = null
            car.photoPassportSecond = null
            car.pathToCarPhoto = null
            car.pathToPassportPhotoFirst = null
            car.pathToPassportPhotoSecond = null
            car.pathToCarPhoto?.let { viewModel.deleteFile(it) }
            car.pathToPassportPhotoFirst?.let { viewModel.deleteFile(it) }
            car.pathToPassportPhotoSecond?.let { viewModel.deleteFile(it) }
            carsModel.listCar.removeAt(carsModel.listCar.size - 1)
        }
    }
}