package com.roman_druck.ampg_printing_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StockBalance(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "count_name")
    val count_name: Double,
    @ColumnInfo (name = "itemInfo")
    val itemInfo: String = "",
    @ColumnInfo (name = "itemChecked")
    val itemChecked: Boolean = false,
    @ColumnInfo (name = "listId")
    val listId: Int,
    @ColumnInfo (name = "itemType")
    val itemType: Int = 0

)
