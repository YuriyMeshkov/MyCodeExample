package com.jobc.j112kilo.utils

fun phoneNumberStringBuild(phone: String) : String {
    val phoneNewBuild = StringBuilder()
    when(phone[1].toString()) {
        "3" -> {
            phone.forEachIndexed { index, c ->
                phoneNewBuild.append(c)
                when(index) {
                    2 -> phoneNewBuild.append(" ").append("(")
                    5 -> phoneNewBuild.append(")").append(" ")
                    8 -> phoneNewBuild.append("-")
                }
            }
        }
        "7" -> {
            phone.forEachIndexed { index, c ->
                phoneNewBuild.append(c)
                when(index) {
                    1 -> phoneNewBuild.append(" ").append("(")
                    4 -> phoneNewBuild.append(")").append(" ")
                    7 -> phoneNewBuild.append("-")
                }
            }
        }
        else -> phoneNewBuild.append(phone)
    }
    return phoneNewBuild.toString()
}