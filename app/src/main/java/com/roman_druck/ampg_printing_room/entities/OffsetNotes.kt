package com.roman_druck.ampg_printing_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "offset_notes")
data class OffsetNotes(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo (name = "number")
    val number: String,
    @ColumnInfo (name = "machine")
    val machine: String = "KBA-Rapida-105U",
    @ColumnInfo (name = "material")
    val material: String,
    @ColumnInfo (name = "paper_format")
    val paper_format: String,
    @ColumnInfo (name = "count_sheets")
    val count_sheets: String,
    @ColumnInfo (name = "paper_thickness")
    val paper_thickness: String,
    @ColumnInfo (name = "paper_density")
    val paper_density: String,
    @ColumnInfo (name = "count_plates")
    val count_plates: String,
    @ColumnInfo (name = "plates_format")
    val plates_format: String,
    @ColumnInfo (name = "count_colors_names")
    val count_colors_names: String,
    @ColumnInfo (name = "description")
    val description: String,
    /*@ColumnInfo (name = "image_simple")
    val photoFileName: String? = null,*/
    @ColumnInfo(name = "count_material")
    val count_material: String,
    @ColumnInfo(name = "count_stock_material")
    val count_stock_material: String,
    @ColumnInfo(name = "remainder_material")
    val remainder_material: String,
    @ColumnInfo(name = "setup_material")
    val setup_material: String,
    @ColumnInfo(name = "inks_print")
    val inks_print_name: String,
    //@ColumnInfo(name = "inks_print_1")
    //val inks_print_name1: String,
    //@ColumnInfo(name = "inks_print_2")
    //val inks_print_name2: String,
    //@ColumnInfo(name = "inks_print_3")
    //val inks_print_name3: String,
    //@ColumnInfo(name = "inks_print_4")
    //val inks_print_name4: String,
    //@ColumnInfo(name = "inks_print_5")
    //val inks_print_name5: String,
    //@ColumnInfo(name = "inks_print_l")
    //val inks_print_nameL: String,
    @ColumnInfo(name = "inks_print_job")
    val inks_print_job: String,
    @ColumnInfo(name = "alko_print_count")
    val alco_print_count: String,
    @ColumnInfo(name = "pH_print_name")
    val pH_print_name: String,
    @ColumnInfo(name = "pH_print_count")
    val pH_print_count: String,
    @ColumnInfo(name = "other_materials_name")
    val other_materials_name: String,
    @ColumnInfo(name = "other_materials_count")
    val other_materials_count: String

): Serializable
