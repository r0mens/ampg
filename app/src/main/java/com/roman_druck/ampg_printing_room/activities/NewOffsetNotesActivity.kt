package com.roman_druck.ampg_printing_room.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.databinding.ActivityNewOffsetNotesBinding
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import com.roman_druck.ampg_printing_room.fragments.OffsetNoteFragment
import com.roman_druck.ampg_printing_room.utils.TimeManager


class NewOffsetNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewOffsetNotesBinding
    private var note: OffsetNotes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewOffsetNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarSettings()
        getNote()

    }
    private fun actionBarSettings() {
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getNote() {
        val sNote = intent.getSerializableExtra(OffsetNoteFragment.NEW_NOTE_OFFSET_KEY)
        if (sNote != null) {
            note = sNote as OffsetNotes
            fillNote()
        }
    }

    private fun fillNote() = with(binding) {
        edName.setText(note?.name)
        edNumber.setText(note?.number)
        edMachine.setText(note?.machine)
        edMaterialName.setText(note?.material)
        edPaperFormat.setText(note?.paper_format)
        edCountSheets.setText(note?.count_sheets)
        edThickness.setText(note?.paper_thickness)
        edDensity.setText(note?.paper_density)
        edCountPlates.setText(note?.count_plates)
        edPlatesFormat.setText(note?.plates_format)
        edCountColorsNames.setText(note?.count_colors_names)
        edDescription.setText(note?.description)
        edCountMaterial.setText(note?.count_material)
        edCountStockMaterial.setText(note?.count_stock_material)
        edRemainderMaterial.setText(note?.remainder_material)
        edSetupMaterial.setText(note?.setup_material)
        edInksName.setText(note?.inks_print_name)
        edInksJob.setText(note?.inks_print_job)
        edAlcoCount.setText(note?.alco_print_count)
        edPHname.setText(note?.pH_print_name)
        edPHCount.setText(note?.pH_print_count)
        edOtherName.setText(note?.other_materials_name)
        edOtherCount.setText(note?.other_materials_count)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_offset_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_save -> {
                setMainResult()
            }
            android.R.id.home -> {
                finish()
            }


        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMainResult() {
        var editState = "new"
        val tempNote: OffsetNotes? = if (note == null) {
            createNewOffsetNote()
        } else {
            editState = "update"
            updateOffsetNote()
        }

        val i = Intent().apply {
            putExtra(OffsetNoteFragment.NEW_NOTE_OFFSET_KEY, tempNote)
            putExtra(OffsetNoteFragment.EDIT_STATE_KEY, editState)

        }
        setResult(RESULT_OK, i)
        finish()
    }

    private fun updateOffsetNote() = with(binding) {
        note?.copy(
            name = edName.text.toString(),
            number = edNumber.text.toString(),
            machine = edMachine.text.toString(),
            material = edMaterialName.text.toString(),
            paper_format = edPaperFormat.text.toString(),
            count_sheets = edCountSheets.text.toString(),
            paper_thickness = edThickness.text.toString(),
            paper_density = edDensity.text.toString(),
            count_plates = edCountPlates.text.toString(),
            plates_format = edPlatesFormat.text.toString(),
            count_colors_names = edCountColorsNames.text.toString(),
            description = edDescription.text.toString(),
            count_material = edCountMaterial.text.toString(),
            count_stock_material = edCountStockMaterial.text.toString(),
            remainder_material = edRemainderMaterial.text.toString(),
            setup_material = edSetupMaterial.text.toString(),
            inks_print_name = edInksName.text.toString(),
            inks_print_job = edInksJob.text.toString(),
            alco_print_count = edAlcoCount.text.toString(),
            pH_print_name = edPHname.text.toString(),
            pH_print_count = edPHCount.text.toString(),
            other_materials_name = edOtherName.text.toString(),
            other_materials_count = edOtherCount.text.toString(),


            )

    }


    private fun createNewOffsetNote(): OffsetNotes {
        return OffsetNotes(
            null,
            binding.edName.text.toString(),
            TimeManager.getCurrentTime(),
            binding.edNumber.text.toString(),
            binding.edMachine.text.toString(),
            binding.edMaterialName.text.toString(),
            binding.edPaperFormat.text.toString(),
            binding.edCountSheets.text.toString(),
            binding.edThickness.text.toString(),
            binding.edDensity.text.toString(),
            binding.edCountPlates.text.toString(),
            binding.edPlatesFormat.text.toString(),
            binding.edCountColorsNames.text.toString(),
            binding.edDescription.text.toString(),
            binding.edCountMaterial.text.toString(),
            binding.edCountStockMaterial.text.toString(),
            binding.edRemainderMaterial.text.toString(),
            binding.edSetupMaterial.text.toString(),
            binding.edInksName.text.toString(),
            binding.edInksJob.text.toString(),
            binding.edAlcoCount.text.toString(),
            binding.edPHname.text.toString(),
            binding.edPHCount.text.toString(),
            binding.edOtherName.text.toString(),
            binding.edOtherCount.text.toString(),


        )
    }
}