package com.jobc.j112kilo.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

fun removeKeyboardFromScreen(v: View, activity: FragmentActivity?) {
    activity?.let {
        val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun removeKeyboardFromScreen(v: View, activity: Activity) {
    activity?.let {
        val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}