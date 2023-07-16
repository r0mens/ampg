package com.roman_druck.ampg_printing_room.entities

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list: List<Int>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(string: String?): List<Int>? {
        return string?.split(",")?.map { it.toInt() }
    }
}
