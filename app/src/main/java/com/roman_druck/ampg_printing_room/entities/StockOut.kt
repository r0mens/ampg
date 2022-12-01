package com.roman_druck.ampg_printing_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stock_out")
data class StockOut(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "material_print")
    val material_out_print: String,
    @ColumnInfo(name = "count_material")
    val count_material: Long,
    @ColumnInfo(name = "inks_print")
    val inks_print_name: String,
    @ColumnInfo(name = "inks_print_count")
    val inks_print_count: Double,
    @ColumnInfo(name = "alko_print_count")
    val alco_print_count: Double,
    @ColumnInfo(name = "pH_print_name")
    val pH_print_name: String,
    @ColumnInfo(name = "pH_print_count")
    val pH_print_count: Double,
    @ColumnInfo(name = "other_materials_name")
    val other_materials_name: String,
    @ColumnInfo(name = "other_materials_count")
    val other_materials_count: Double

)
