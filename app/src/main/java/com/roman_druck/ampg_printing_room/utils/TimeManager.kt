package com.roman_druck.ampg_printing_room.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeManager {
     fun getCurrentTime(): String{
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)
    }
}