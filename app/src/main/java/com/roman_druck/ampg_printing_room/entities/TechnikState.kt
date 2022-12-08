package com.roman_druck.ampg_printing_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "technik_state")
data class TechnikState(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "data")
    val date: String,
    @ColumnInfo(name = "name_machine")
    val name_machine: String,
    @ColumnInfo(name = "number_machine")
    val number_machine: String,
    @ColumnInfo(name = "name_spareparts")
    val name_spareparts: String,


)
