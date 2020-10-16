package com.jobc.j112kilo.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.afterTextDateChange(
    afterTextDateChange: (String) -> Unit
) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
            val str = p0.toString()
            var strBuild = ""
            when(str.length) {
                0 -> afterTextDateChange.invoke(str)
                1 -> strBuild = try {
                    when (str.toInt() in 0..3) {
                        true -> str
                        false -> ""
                    }
                } catch (e: Exception) {
                    ""
                }
                2 -> strBuild = try {
                    when (str.toInt() in 1..31) {
                        true -> "$str."
                        false -> ""
                    }
                } catch (e: Exception) {
                    ""
                }
                3 -> strBuild = str
                4 -> strBuild = try {
                    when (str[3].toString().toInt() in 0..1) {
                        true -> str
                        false -> str.take(3)
                    }
                } catch (e: Exception) {
                    str.take(3)
                }
                5 -> strBuild = try {
                    when (str.substring(3, 5).toInt() in 1..12) {
                        true -> "$str."
                        false -> str.take(4)
                    }
                } catch (e: Exception) {
                    str.take(4)
                }
                6 -> strBuild = str
                7 -> strBuild = try {
                    when (str[6].toString().toInt() in 1..2) {
                        true -> str
                        false -> str.take(6)
                    }
                } catch (e: Exception) {
                    str.take(6)
                }
                8 -> strBuild = try {
                    when (str.substring(6, 8).toInt() in 19..20) {
                        true -> str
                        false -> str.take(7)
                    }
                } catch (e: Exception) {
                    str.take(7)
                }
                9 -> strBuild = try {
                    when (str[8].toString().toInt() in 0..9) {
                        true -> str
                        false -> str.take(8)
                    }
                } catch (e: Exception) {
                    str.take(8)
                }
                10 -> strBuild = try {
                    when (str[9].toString().toInt() in 0..9) {
                        true -> str
                        false -> str.take(9)
                    }
                } catch (e: Exception) {
                    str.take(9)
                }
                else -> strBuild = str.take(10)
            }
            afterTextDateChange.invoke(strBuild)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    })
}