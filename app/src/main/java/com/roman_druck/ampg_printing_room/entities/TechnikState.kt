package com.roman_druck.ampg_printing_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "technik_state")
data class TechnikState(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name_list")
    val name_list: String,
    @ColumnInfo(name = "data")
    val date: String,
    @ColumnInfo(name = "itemsIds")
    val itemsIds: String? = null



): Serializable
