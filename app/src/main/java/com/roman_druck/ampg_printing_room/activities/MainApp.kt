package com.roman_druck.ampg_printing_room.activities

import android.app.Application
import com.roman_druck.ampg_printing_room.db.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this) }
}