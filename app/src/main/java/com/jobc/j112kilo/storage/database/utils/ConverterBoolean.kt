package com.jobc.j112kilo.storage.database.utils

import androidx.room.TypeConverter

class ConverterBoolean {
    @TypeConverter
    fun toBoolean(booleanStr: String) : Boolean =
        when(booleanStr) {
            "1" -> true
            else -> false
        }
    @TypeConverter
    fun toString(strBoolean: Boolean) : String =
        when(strBoolean) {
            true -> "1"
            false -> "0"
        }
}