package com.jobc.j112kilo.ui.imgtransformation

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.imgtransformation.di.ImgTransformComponent
import com.jobc.j112kilo.ui.imgtransformation.utils.CroppedBitmapUtil
import com.jobc.j112kilo.utils.*
//import kotlinx.android.synthetic.main.fragment_image_transformation_avatar.*
//import kotlinx.android.synthetic.main.fragment_image_transformation_license.*
import java.lang.ArithmeticException
import javax.inject.Inject

class ImgTransformationFragment : Fragment() { //}, View.OnTouchListener {

    companion object {

        private const val IMAGE_URL = "image_url"
        private const val PATH_FILE = "path_file"
        private const val NAME_FILE = "name_file"
        private const val GALLERY_OR_TAKE_PICTURES = "gallery_or_take_picture"
        private const val CHOICE_LAYOUT = "choice_layout"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param url url photo для галереи.
         * @param filePath путь фото для камеры.
         * @param galleryOrTakePicture определяет от куда брать фото галерея или камера
         * @return Bundle().
         */

        @JvmStatic
        fun newArgument(
            url: String,
            filePath: String,
            fileName: String,
            galleryOrTakePicture: String,
            choiceLayout: String
        ) =
            Bundle().apply {
                putString(IMAGE_URL, url)
                putString(PATH_FILE, filePath)
                putString(NAME_FILE, fileName)
                putString(GALLERY_OR_TAKE_PICTURES, galleryOrTakePicture)
                putString(CHOICE_LAYOUT, choiceLayout)
            }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: ImgTransformationViewModel by viewModels { factory }
    private lateinit var scaleDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector
    private lateinit var selectedBitmap: Bitmap
    private lateinit var pathFile: String
    private lateinit var nameFile: String
    private lateinit var receivedBitmap: Bitmap
    private lateinit var galleryOrTakePicture: String
    private lateinit var selectedImageUrl: String
    private lateinit var choiceLayout: String
    private var heightToWidthRatio = 1.0
    private var scaleFactor: Float = 1f
    private var motionDownX: Float = 0f
    private var motionDownY: Float = 0f

    private lateinit var ivTransformation: ImageView
    private lateinit var pbLoadOrWriteImage: ProgressBar
    private lateinit var btnAccept: Button
    private lateinit var btnCancel: Button
    private lateinit var btnRotate: ImageButton

    @LayoutRes
    private fun getLayoutResId() : Int {
        return when(choiceLayout) {
            LAYOUT_FOR_AVATAR -> {
                /** heightToWidthRatio устанавливает */
                heightToWidthRatio = 1.0
                R.layout.fragment_image_transformation_avatar
            }
            else -> {
                heightToWidthRatio = 0.7
                R.layout.fragment_image_transformation_license
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImgTransformComponent.init((requireActivity().application as App).appComponent)
            .inject(this)
        getDataFromArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(getLayoutResId(), container, false)
        initWidgets(v)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingImage(selectedImageUrl, galleryOrTakePicture)
        initLiveData(view)
        initButtons()
        initGestureDetector()
        transformationImage(view)
    }

    private fun initLiveData(view: View) {
        initProgressBarLiveData()
        initImageLoadedLiveData()
        initErrorLiveData(view)
    }

    private fun initWidgets(view: View) {
        ivTransformation = view.findViewById(R.id.ivTransformation)
        pbLoadOrWriteImage= view.findViewById(R.id.pbLoadOrWriteImage)
        btnAccept = view.findViewById(R.id.btnAccept)
        btnCancel = view.findViewById(R.id.btnCancel)
        btnRotate = view.findViewById(R.id.btnRotate)
    }

    private fun initImageLoadedLiveData() {
        observer(viewModel.loadImg) {
            receivedBitmap = it
            getBitmapSizeImageView()
            ivTransformation.setImageBitmap(receivedBitmap)
        }
    }

    private fun initProgressBarLiveData() {
        observer(viewModel.writingOrLoading) {
            when(it) {
                true -> pbLoadOrWriteImage.visibility = View.VISIBLE
                false -> pbLoadOrWriteImage.visibility =View.GONE
            }
        }
    }

    private fun initErrorLiveData(view: View) {
        observer(viewModel.error) {
            Snackbar.make(view, getString(R.string.error_loading_image), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun transformationImage(view: View) {
       view.setOnTouchListener { _, event ->
           event?.let{
               scaleDetector.onTouchEvent(it)
               gestureDetector.onTouchEvent(it)
           }
           true
       }
    }

    private fun getDataFromArguments() {
        arguments?.let {
            selectedImageUrl = it.getString(IMAGE_URL) ?: ERROR_DATA
            pathFile = it.getString(PATH_FILE) ?: ERROR_DATA
            nameFile = it.getString(NAME_FILE) ?: ERROR_DATA
            galleryOrTakePicture = it.getString(GALLERY_OR_TAKE_PICTURES) ?: ERROR_DATA
            choiceLayout = it.getString(CHOICE_LAYOUT) ?: ERROR_DATA
        }
    }

    private fun loadingImage(url: String, choiceSourceImage: String) {
        when(choiceSourceImage) {
            TAKE_PICTURE -> {
                viewModel.loadingImage(url)
            }
            SEARCH_PHOTO_IN_GALLERY -> {
                activity?.let {
                    val contentResolver = it.contentResolver
                    viewModel.loadingImageFromGallery(url, contentResolver)
                }

            }
        }
    }

    private fun initButtons() {
        initBtnAccept()
        initBtnCancel()
        initBtnRotate()
    }

    private fun initBtnAccept() {
        btnAccept.setOnClickListener{
            selectedBitmap = ivTransformation.drawable.toBitmap()
            selectedBitmap = CroppedBitmapUtil()
                .getCroppedImage(ivTransformation, selectedBitmap, heightToWidthRatio)
            //ivTransformation.setImageBitmap(selectedBitmap)
            writeImage()
            activity?.finish()
        }
    }

    private fun initBtnCancel() {
        btnCancel.setOnClickListener {
            activity?.finish()
        }
    }

    private fun initBtnRotate() {
        btnRotate.setOnClickListener {
            bitmapRotate()
        }
    }

    private fun initGestureDetector() {
        scaleDetector = ScaleGestureDetector(activity, scaleListener)
        gestureDetector = GestureDetector(activity, gestureListener)
    }

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= detector!!.scaleFactor
            scaleFactor = when (scaleFactor < 1) {
                true -> 1f
                false -> scaleFactor
            }
            scaleFactor = (scaleFactor * 100).toInt().toFloat() / 100
            ivTransformation.scaleX = scaleFactor
            ivTransformation.scaleY = scaleFactor
            return true
        }
    }

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            e?.let {
                motionDownX = e.rawX - ivTransformation.translationX
                motionDownY = e.rawY - ivTransformation.translationY
                return true
            }
            return false
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            e2?.let{
                ivTransformation.translationX = e2.rawX - motionDownX
                ivTransformation.translationY = e2.rawY - motionDownY
                return true
            }
            return false
        }
    }

    private fun getBitmapSizeImageView() {
        val width = receivedBitmap.width
        val height = receivedBitmap.height
        val ivWidth = ivTransformation.width
        val ivHeight= ivTransformation.height
        try {
            when (width > height) {
                true -> {
                    if (width < ivWidth) {
                        scaleFactor = (ivWidth / width).toFloat()
                        scaleImageView()
                    } else {
                        val bitmapToImageViewRatio = width / ivWidth + 1
                        setSizeBitmap(width, height, bitmapToImageViewRatio)
                    }
                }
                false -> {
                    if (height < ivHeight) {
                        scaleFactor = (ivHeight / height).toFloat()
                        scaleImageView()
                    } else {
                        val bitmapToImageViewRatio = height / ivHeight + 1
                        setSizeBitmap(width, height, bitmapToImageViewRatio)
                    }
                }
            }
        } catch (e: ArithmeticException) {
            // Если ошибка, ничего не изменяем
        }
    }

    private fun scaleImageView() {
        ivTransformation.scaleX = scaleFactor
        ivTransformation.scaleY = scaleFactor
    }

    private fun setSizeBitmap(width: Int, height: Int, ratio: Int) {
        receivedBitmap = Bitmap.createScaledBitmap(
            receivedBitmap,
            width / ratio,
            height / ratio,
            true
        )
    }

    private fun bitmapRotate() {
        val width = receivedBitmap.width
        val height = receivedBitmap.height
        val matrix = Matrix().apply {
            postRotate(90F)
        }
        receivedBitmap = Bitmap.createBitmap(receivedBitmap, 0, 0, width, height, matrix, true)
        ivTransformation.setImageBitmap(receivedBitmap)
    }

    private fun writeImage() {
        viewModel.writeImage(selectedBitmap, nameFile,  pathFile)
    }

}