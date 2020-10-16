package com.jobc.j112kilo.ui.imgtransformation.utils

import android.graphics.Bitmap
import android.widget.ImageView

class CroppedBitmapUtil {

    private lateinit var selectedBitmap: Bitmap
    private var angleLeftTopX = 0f
    private var angleLeftTopY = 0f
    private var angleRightTopX = 0f
    private var angleLeftBottomY = 0f
    private var widthSelectedBitmap = 0
    private var heightSelectedBitmap = 0
    private var widthCropView = 0
    private var heightCropView = 0

    private var xyScale = 0
    private var xTop = 0
    private var yTop = 0
    private var widthCrop = 0
    private var heightCrop= 0

    private val sizeWidth = 500
    private var sizeHeight = 500

    fun getCroppedImage(image: ImageView, bitmap: Bitmap,
                        heightToWidthRatio: Double) : Bitmap {
        sizeHeight = (sizeWidth * heightToWidthRatio).toInt()
        getCoordinatesBitmap(image, bitmap)
        getBitmapCroppingOptions()
        createCroppedBitmap(bitmap)
        setSizeOfBitmap()
        return selectedBitmap
    }

    private fun getCoordinatesBitmap(image: ImageView, bitmap: Bitmap) {
        val xTranslation = image.translationX
        val yTranslation = image.translationY
        xyScale = image.scaleX.toInt()
        widthSelectedBitmap = bitmap.width
        heightSelectedBitmap = bitmap.height
        widthCropView = image.width
        heightCropView = image.height

        val centerCropBitmapX = widthSelectedBitmap / 2
        val centerCropBitmapY = heightSelectedBitmap / 2
        val centerCropOffsetX = centerCropBitmapX - xTranslation / xyScale
        val centerCropOffsetY = centerCropBitmapY - yTranslation / xyScale

        angleLeftTopX = centerCropOffsetX - widthCropView/ 2 / xyScale
        angleLeftTopY = centerCropOffsetY - heightCropView/ 2 / xyScale
        angleRightTopX = centerCropOffsetX + widthCropView/ 2 / xyScale
        angleLeftBottomY = centerCropOffsetY + heightCropView/ 2 / xyScale
    }

    private fun getBitmapCroppingOptions() {
        when {
            angleLeftTopX < 0 && angleRightTopX < widthSelectedBitmap -> {
                widthCrop = (widthCropView / xyScale + angleLeftTopX).toInt()
            }
            angleLeftTopX < 0 && angleRightTopX > widthSelectedBitmap -> {
                widthCrop = widthSelectedBitmap
            }
            angleLeftTopX > 0 && angleRightTopX < widthSelectedBitmap -> {
                xTop = angleLeftTopX.toInt()
                widthCrop = (widthCropView / xyScale)
            }
            angleLeftTopX > 0 && angleRightTopX > widthSelectedBitmap -> {
                xTop = angleLeftTopX.toInt()
                widthCrop = widthSelectedBitmap - xTop
            }
        }

        when {
            angleLeftTopY < 0 && angleLeftBottomY < heightSelectedBitmap -> {
                heightCrop = (heightCropView / xyScale + angleLeftTopY).toInt()
            }
            angleLeftTopY < 0 && angleLeftBottomY > heightSelectedBitmap -> {
                heightCrop = heightSelectedBitmap
            }
            angleLeftTopY > 0 && angleLeftBottomY < heightSelectedBitmap -> {
                yTop = angleLeftTopY.toInt()
                heightCrop = (heightCropView / xyScale)
            }
            angleLeftTopY > 0 && angleLeftBottomY > heightSelectedBitmap -> {
                yTop = angleLeftTopY.toInt()
                heightCrop = heightSelectedBitmap - yTop

            }
        }
    }

    private fun createCroppedBitmap(bitmap: Bitmap) {
        selectedBitmap = try {
            Bitmap.createBitmap(
                bitmap,
                xTop,
                yTop,
                widthCrop,
                heightCrop
            )
        } catch (e: IllegalArgumentException) {
            bitmap
        }
    }

    private fun setSizeOfBitmap() {
        widthSelectedBitmap = selectedBitmap.width
        heightSelectedBitmap = selectedBitmap.height
        var divideOrMultiply = false //умножать

        when(widthSelectedBitmap > heightSelectedBitmap) {
            true -> {
                when(widthSelectedBitmap > sizeWidth) {
                    true -> {
                        xyScale = widthSelectedBitmap / sizeWidth
                        divideOrMultiply = true // делить
                    }
                    false -> {
                        xyScale = sizeWidth / widthSelectedBitmap
                        divideOrMultiply = false //умножать
                    }
                }
            }
            false -> {
                when(heightSelectedBitmap > sizeHeight) {
                    true -> {
                        xyScale = heightSelectedBitmap / sizeHeight
                        divideOrMultiply = true // делить
                    }
                    false -> {
                        xyScale = sizeHeight / heightSelectedBitmap
                        divideOrMultiply = false //умножать
                    }
                }
            }
        }

        if (xyScale == 0) {
            xyScale = 1
        }

        selectedBitmap =
            if(divideOrMultiply) {
                Bitmap.createScaledBitmap(
                    selectedBitmap,
                    widthSelectedBitmap / xyScale,
                    heightSelectedBitmap / xyScale,
                    true
                )
            } else {
                Bitmap.createScaledBitmap(
                    selectedBitmap,
                    widthSelectedBitmap * xyScale,
                    heightSelectedBitmap * xyScale,
                    true
                )
            }
    }
}