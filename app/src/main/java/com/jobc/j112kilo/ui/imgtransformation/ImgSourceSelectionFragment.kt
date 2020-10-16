package com.jobc.j112kilo.ui.imgtransformation

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jobc.j112kilo.R
import com.jobc.j112kilo.utils.*
import kotlinx.android.synthetic.main.fragment_photo_source_selection.*
import java.io.File

class ImgSourceSelectionFragment : Fragment() {

    companion object {

        private const val REQUEST_PHOTO = 1
        private const val REQUEST_GALLERY = 2
        private const val PATH_FILE = "extra_path_file"
        private const val FILE_NAME = "extra_file_name"
        private const val CHOICE_LAYOUT = "choice_layout"

        fun getArgument(pathFile: String, fileName: String, choiceLayout: String) =
            bundleOf(
                FILE_NAME to fileName,
                PATH_FILE to pathFile,
                CHOICE_LAYOUT to choiceLayout
            )
    }

    private lateinit var navController: NavController
    private lateinit var filePath: String
    private lateinit var fileName: String
    private lateinit var choiceLayout: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_source_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController(view)
        initButtons()
    }

    private fun getDataFromActivity() {
        arguments?.let {
            filePath = it.getString(PATH_FILE) ?: ERROR_DATA
            fileName = it.getString(FILE_NAME) ?: ERROR_DATA
            choiceLayout = it.getString(CHOICE_LAYOUT) ?: ERROR_DATA
        }
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initButtons() {
        initBtnTakePictures()
        initBtnGallerySearch()
        initBtnCancel()
    }

    private fun initBtnTakePictures() {
        btnTakePictures.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            activity?.let { context ->
                intent.resolveActivity(context.packageManager).let {
                    val uri: Uri = getUri(context)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    setWritePermissionForUri(uri, context)
                    startActivityForResult(intent, REQUEST_PHOTO)
                }
            }

        }
    }

    private fun setWritePermissionForUri (uri: Uri, context: FragmentActivity) {
        val cameraActivities: List<ResolveInfo> = context.packageManager.queryIntentActivities(
            context.intent, PackageManager.MATCH_DEFAULT_ONLY
        )
        cameraActivities.forEach {
            context.grantUriPermission(
                it.activityInfo.packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
        }
    }

    private fun getUri(context: FragmentActivity) : Uri =
        FileProvider.getUriForFile(
            context,
            "com.jobc.j112kilo.fileprovider",
            getFilePathPhoto()
        )

    private fun getFilePathPhoto() : File {
        val filePathForPhoto = File(filePath)
        if(!filePathForPhoto.exists()) {
            filePathForPhoto.mkdirs()
        }
        return File(filePathForPhoto, fileName)
    }

    private fun initBtnGallerySearch() {
        btnGallerySearch.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_GALLERY)
        }
    }

    private fun initBtnCancel() {
        btnCancelChoice.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == 1 && resultCode == Activity.RESULT_OK -> {
                val url = StringBuilder()
                    .append(filePath)
                    .append(fileName)
                    .toString()
                navController.navigate(
                    R.id.imageTransformationFragment,
                    ImgTransformationFragment.newArgument(
                        url,
                        filePath,
                        fileName,
                        TAKE_PICTURE,
                        choiceLayout
                    )
                )
            }

            requestCode == 2 && resultCode == Activity.RESULT_OK -> {
                val uri = data?.data.toString()
                navController.navigate(
                    R.id.imageTransformationFragment,
                    ImgTransformationFragment.newArgument(
                        uri,
                        filePath,
                        fileName,
                        SEARCH_PHOTO_IN_GALLERY,
                        choiceLayout
                    )
                )
            }
        }
    }


}