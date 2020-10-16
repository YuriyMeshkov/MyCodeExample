package com.jobc.j112kilo.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.afterTextPhoneChanged(
    country: String?,
    afterTextPhoneChanged: (String) -> Unit
) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
            if (country == null) {
                afterTextPhoneChanged.invoke(p0.toString())
            } else {
                var strBuild = ""
                val strNumber: String = p0.toString()
                when(country) {
                    "UA" -> strBuild = textPhoneForUAorBY(strNumber, "+38 (")
                    "BY" -> strBuild = textPhoneForUAorBY(strNumber, "+37 (")
                    "RU" -> strBuild = textPhoneForRUorKZ(strNumber,"+7 (")
                    "KZ" -> strBuild = textPhoneForRUorKZ(strNumber, "+7 (")
                }
                afterTextPhoneChanged.invoke(strBuild)
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    })
}

private fun textPhoneForUAorBY(strNumber: String, countryNum: String) : String {
    var strBuild: String
    when(strNumber.length) {
        0 -> strBuild = countryNum
        1 -> strBuild = countryNum
        2 -> strBuild = countryNum
        3 -> strBuild = countryNum
        4 -> strBuild = countryNum
        5 -> strBuild = countryNum
        6 -> {
            strBuild = try{
                strNumber[5].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(5)
            }
        }
        7 -> {
            strBuild = try{
                strNumber[6].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(6)
            }
        }
        8 -> {
            strBuild = try{
                strNumber[7].toInt()
                "$strNumber) "
            } catch(e: Exception) {
                strNumber.take(8)
            }
        }
        9 -> {
            strBuild = strNumber.take(8) + ")"
        }
        10 -> {
            strBuild = strNumber.take(9) + " "
        }
        11 -> {
            strBuild = try{
                strNumber[10].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(11)
            }
        }
        12 -> {
            strBuild = try{
                strNumber[11].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(12)
            }
        }
        13 -> {
            strBuild = try{
                strNumber[12].toInt()
                "$strNumber-"
            } catch(e: Exception) {
                strNumber.take(13)
            }
        }
        14 -> strBuild = strNumber.take(13) + "-"
        15 -> {
            strBuild = try{
                strNumber[14].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(15)
            }
        }
        16 -> {
            strBuild = try{
                strNumber[15].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(16)
            }
        }
        17 -> {
            strBuild = try{
                strNumber[16].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(17)
            }
        }
        18 -> {
            strBuild = try{
                strNumber[17].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(18)
            }
        }
        else -> strBuild = strNumber.take(18)
    }
    strBuild.forEachIndexed { index, c ->
        try {
            if (index != 0) {
                if (index != 3) {
                    if (index != 4) {
                        if (index != 8) {
                            if (index != 9) {
                                if (index != 13) {
                                    c.toString().toInt()
                                }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            strBuild = countryNum
        }
    }
    return strBuild
}

private fun textPhoneForRUorKZ(strNumber: String, countryNum: String) : String {
    var strBuild: String
    when(strNumber.length) {
        0 -> strBuild = countryNum
        1 -> strBuild = countryNum
        2 -> strBuild = countryNum
        3 -> strBuild = countryNum
        4 -> strBuild = countryNum
        5 -> {
            strBuild = try{
                strNumber[4].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(4)
            }
        }
        6 -> {
            strBuild = try{
                strNumber[5].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(5)
            }
        }
        7 -> {
            strBuild = try{
                strNumber[6].toInt()
                "$strNumber) "
            } catch(e: Exception) {
                strNumber.take(7)
            }
        }
        8 -> {
            strBuild = strNumber.take(7) + ")"
        }
        9 -> {
            strBuild = strNumber.take(8) + " "
        }
        10 -> {
            strBuild = try{
                strNumber[9].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(10)
            }
        }
        11 -> {
            strBuild = try{
                strNumber[10].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(11)
            }
        }
        12 -> {
            strBuild = try{
                strNumber[11].toInt()
                "$strNumber-"
            } catch(e: Exception) {
                strNumber.take(12)
            }
        }
        13 -> strBuild = strNumber.take(12) + "-"
        14 -> {
            strBuild = try{
                strNumber[13].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(14)
            }
        }
        15 -> {
            strBuild = try{
                strNumber[14].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(15)
            }
        }
        16 -> {
            strBuild = try{
                strNumber[14].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(16)
            }
        }
        17 -> {
            strBuild = try{
                strNumber[16].toInt()
                strNumber
            } catch(e: Exception) {
                strNumber.take(17)
            }
        }
        else -> strBuild = strNumber.take(17)
    }
    strBuild.forEachIndexed { index, c ->
        try {
            if (index != 0) {
                if (index != 2) {
                    if (index != 3) {
                        if (index != 7) {
                            if (index != 8) {
                                if (index != 12) {
                                    c.toString().toInt()
                                }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            strBuild = countryNum
        }
    }
    return strBuild
}