package com.roman_druck.ampg_printing_room.utils

import android.content.Intent
import com.roman_druck.ampg_printing_room.entities.OffsetNotes

object ShareHelperManager {
    fun shareOfsetList(note: List<OffsetNotes>): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plane"
        intent.apply {
            putExtra(Intent.EXTRA_TEXT, makeShareText(note))
        }
        return intent
    }



    private fun makeShareText(note: List<OffsetNotes>): String{
        val sBuilder = StringBuilder()

        sBuilder.append("\n")
        var counter = 0
        note.forEach{
            //sBuilder.append("${counter++}")
            sBuilder.append("Название заказа: ${it.name} ")
            sBuilder.append("\n")
            sBuilder.append("Заказ№: ${it.number} ")
            sBuilder.append("\n")
            sBuilder.append("Машина: ${it.machine} ")
            sBuilder.append("\n")
            sBuilder.append("Дата:${it.date} ")
            sBuilder.append("\n")
            sBuilder.append("Материал:${it.material} ")
            sBuilder.append("\n")
            sBuilder.append("Формат(мм.):${it.paper_format} ")
            sBuilder.append("\n")
            sBuilder.append("Тираж:${it.count_sheets} ")
            sBuilder.append("\n")
            sBuilder.append("Толщина(мм.):${it.paper_thickness} ")
            sBuilder.append("\n")
            sBuilder.append("Плотность(г/м2)${it.paper_density} ")
            sBuilder.append("\n")
            sBuilder.append("Количество форм:${it.count_plates} ")
            sBuilder.append("\n")
            sBuilder.append("Формат форм(мм.)${it.plates_format} ")
            sBuilder.append("\n")
            sBuilder.append("Цвета:${it.count_colors_names} ")
            sBuilder.append("\n")
            sBuilder.append("Примечания:${it.description} ")
            sBuilder.append("\n")
            sBuilder.append("Отпечатано:${it.count_material} ")
            sBuilder.append("\n")
            sBuilder.append("Название краски:${it.inks_print_name} ")
            sBuilder.append("\n")
            sBuilder.append("Расход краски:${it.inks_print_job} ")
            sBuilder.append("\n")
            sBuilder.append("Расход спирта:${it.alco_print_count} ")
            sBuilder.append("\n")
            sBuilder.append("рН название:${it.pH_print_name} ")
            sBuilder.append("\n")
            sBuilder.append("рН расход${it.pH_print_count} ")
            sBuilder.append("\n")
            sBuilder.append("Другие материалы:${it.other_materials_name} ")
            sBuilder.append("\n")
            sBuilder.append("Расход другихматериалов:${it.other_materials_count} ")
            sBuilder.append("\n")

        }
        return sBuilder.toString()
    }


}