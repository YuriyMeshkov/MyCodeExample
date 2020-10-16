package com.jobc.j112kilo.ui.imgtransformation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.jobc.j112kilo.R
import com.jobc.j112kilo.utils.ERROR_DATA

class ImgTransformationActivity : AppCompatActivity(R.layout.activity_image_transformation) {

    companion object {

        private const val EXTRA_PATH_FILE = "extra_path_file"
        private const val EXTRA_FILE_NAME = "extra_file_name"
        private const val EXTRA_CHOICE_LAYOUT = "choice_layout"

        fun newIntent(
            context: Context,
            pathFile: String,
            fileName: String,
            choiceLayout: String
        ) : Intent {
            val intent = Intent(context, ImgTransformationActivity::class.java)
            intent.putExtra(EXTRA_PATH_FILE, pathFile)
            intent.putExtra(EXTRA_FILE_NAME, fileName)
            intent.putExtra(EXTRA_CHOICE_LAYOUT, choiceLayout)
            return intent
        }
    }

    private val navController by lazy { findNavController(R.id.my_nav_host_transformation_fragment) }
    //private lateinit var navController: NavController
    private lateinit var pathFile: String
    private lateinit var fileName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getDataFromIntent()
        navController.setGraph(R.navigation.nav_img_transformation_graph, getArgument())
        /*navController = Navigation.findNavController(
            this,
            R.id.my_nav_host_transformation_fragment
        )*/
    }

    private fun getDataFromIntent() {
        pathFile = intent.getStringExtra(EXTRA_PATH_FILE) ?: ERROR_DATA
        fileName = intent.getStringExtra(EXTRA_FILE_NAME) ?: ERROR_DATA
    }

    private fun getArgument() : Bundle =
        ImgSourceSelectionFragment.getArgument(
            intent.getStringExtra(EXTRA_PATH_FILE) ?: ERROR_DATA,
            intent.getStringExtra(EXTRA_FILE_NAME) ?: ERROR_DATA,
            intent.getStringExtra(EXTRA_CHOICE_LAYOUT) ?: ERROR_DATA
        )
}