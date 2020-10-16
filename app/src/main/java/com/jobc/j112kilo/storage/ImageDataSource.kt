package com.jobc.j112kilo.storage

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.FileInputStream
import java.io.InputStream

class ImageDataSource {

    fun writeImage(bitmap: Bitmap, fileName: String, filePath: String) {
        val path = File(filePath)
        if (!path.exists()) path.mkdirs()
        val myPath = File(path, fileName)
        var fos: FileOutputStream? = null
        try {
            fos = myPath.outputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        } finally {
            fos?.close()
        }
    }

    fun loadingImage(pathImage: String): Bitmap {
        val path = File(pathImage)
        val fileInputStream: FileInputStream? = null
        return try {
            val fis = path.inputStream()
            BitmapFactory.decodeStream(fis)
        }  finally {
            fileInputStream?.close()
        }
    }

    fun loadingImageFromGallery(
        pathImage: String,
        contentResolver: ContentResolver
    ): Bitmap {
        val uri = Uri.parse(pathImage)
        var inputStream: InputStream? = null
        return try {
            inputStream = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } finally {
            inputStream?.close()
        }
    }

    fun deleteFile(pathFile: String) {
        val myPath = File(pathFile)
        if (myPath.exists()) {
            myPath.delete()
        }
    }
}