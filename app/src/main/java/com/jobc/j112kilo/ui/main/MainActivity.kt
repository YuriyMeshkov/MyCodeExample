package com.jobc.j112kilo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jobc.j112kilo.App
import com.jobc.j112kilo.R
import com.jobc.j112kilo.data.model.UserModel
import com.jobc.j112kilo.ui.main.di.MainComponent
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainComponent.init((this.application as App).appComponent)
            .inject(this)

        ivAvatar.setImageBitmap(user.avatar)

        btnClear.setOnClickListener {
            val deleteFiles = File(getPathToFile())
            if (deleteFiles.exists()) {
                deleteFiles.deleteRecursively()
            }
            val deleteDatabase = File(getPathToDatabase())
            if (deleteDatabase.exists()) {
                deleteDatabase.deleteRecursively()
            }

            this.finish()
        }
    }

    private fun getPathToFile() =
        StringBuilder()
            .append(this.filesDir.toString())
            .toString()

    private fun getPathToDatabase() =
        StringBuilder()
            .append(this.applicationInfo.dataDir)
            .append("/databases")
            .toString()

}