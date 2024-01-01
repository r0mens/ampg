package com.roman_druck.ampg_printing_room.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.activities.MainApp
import com.roman_druck.ampg_printing_room.activities.NewOffsetNotesActivity
import com.roman_druck.ampg_printing_room.databinding.FragmentOffsetNoteBinding
import com.roman_druck.ampg_printing_room.db.MainViewModel
import com.roman_druck.ampg_printing_room.db.OffsetNoteAdapter
import com.roman_druck.ampg_printing_room.dialogs.DeleteDialog
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.ByteArrayInputStream
import java.io.File


class OffsetNoteFragment : BaseFragment(), OffsetNoteAdapter.Listener {
    private lateinit var binding: FragmentOffsetNoteBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: OffsetNoteAdapter
    private lateinit var sheet: XSSFSheet
    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        editLauncher.launch(Intent(activity, NewOffsetNotesActivity::class.java))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onEditResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOffsetNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding) {
        rcViewOffsetNotes.layoutManager = LinearLayoutManager(activity)
        adapter = OffsetNoteAdapter(this@OffsetNoteFragment)
        rcViewOffsetNotes.adapter = adapter
    }

     private fun observer() {
        mainViewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    private fun onEditResult() {
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val editState = it.data?.getStringExtra(EDIT_STATE_KEY)
                if (editState == "update") {
                    mainViewModel.updateNote(it.data?.getSerializableExtra(NEW_NOTE_OFFSET_KEY) as OffsetNotes)
                } else {
                    mainViewModel.insertNote(it.data?.getSerializableExtra(NEW_NOTE_OFFSET_KEY) as OffsetNotes)
                }

            }
        }
    }


    override fun deleteItem(id: Int) {
        DeleteDialog.showDialog(context as AppCompatActivity, object : DeleteDialog.Listener{
            override fun onClick(){
                mainViewModel.deleteNote(id)
            }

        })

    }

    override fun onClickItem(note: OffsetNotes) {
        val intent = Intent(activity, NewOffsetNotesActivity::class.java).apply {
            putExtra(NEW_NOTE_OFFSET_KEY, note)
        }
        editLauncher.launch(intent)
    }
    private fun fillXLSMFile(tempNote: OffsetNotes?): XSSFWorkbook {
        val resourceId = R.raw.chablon
        val inputStream = resources.openRawResource(resourceId)
        val templateByteArray = inputStream.readBytes()
        inputStream.close()

        val workbook = XSSFWorkbook(ByteArrayInputStream(templateByteArray))
        sheet = workbook.getSheetAt(0)

        val dataCoordinates = listOf(
            convertToNumericCoordinates("B1"),
            convertToNumericCoordinates("D1"),
            convertToNumericCoordinates("J1"),
            convertToNumericCoordinates("J2"),
            convertToNumericCoordinates("A20"),
            convertToNumericCoordinates("D20"),
            convertToNumericCoordinates("B2"),
            convertToNumericCoordinates("E20"),
            convertToNumericCoordinates("H14"),
            convertToNumericCoordinates("G15"),
            convertToNumericCoordinates("G20"),//отпечатано материала
            convertToNumericCoordinates("F20"),//получено материала
            convertToNumericCoordinates("H20"),//остаток материала
            convertToNumericCoordinates("I20"),//приладка
            convertToNumericCoordinates("C4"),//название краски
            convertToNumericCoordinates("C17"),//количество спирта
            convertToNumericCoordinates("B16"),//pH название
            convertToNumericCoordinates("B17"),//pH количество->
            convertToNumericCoordinates("C6"),//название1
            convertToNumericCoordinates("D6"),//название2
            convertToNumericCoordinates("E6"),//название3
            convertToNumericCoordinates("F6"),//название4
            convertToNumericCoordinates("G6"),//название5
            convertToNumericCoordinates("H6"),//название6
            convertToNumericCoordinates("E2"),//размер готового изделия
            convertToNumericCoordinates("H2"),//штук на листе
            convertToNumericCoordinates("J5"),//примечание 1
            convertToNumericCoordinates("J6"),//примечание 2
            convertToNumericCoordinates("C12"),//расход краски 1 секция
            convertToNumericCoordinates("D12"),//расход краски 2 секция
            convertToNumericCoordinates("E12"),//расход краски 3 секция
            convertToNumericCoordinates("F12"),//расход краски 4 секция
            convertToNumericCoordinates("G12"),//расход краски 5 секция
            convertToNumericCoordinates("H12"),//расход краски L секция
            convertToNumericCoordinates("D16"),//очиститель название
            convertToNumericCoordinates("E16"),//другое название
            convertToNumericCoordinates("G16"),//подкладка(шт)
            convertToNumericCoordinates("D17"),//очиститель количество
            convertToNumericCoordinates("E17"),//другое количество
            convertToNumericCoordinates("J14"),//резина количество




            // Добавьте другие координаты
        )

        val dataValues = mutableListOf(
            tempNote?.date,
            tempNote?.name,
            tempNote?.number,
            tempNote?.machine,
            tempNote?.material,
            tempNote?.paper_format,
            tempNote?.count_sheets,
            tempNote?.paper_density,
            tempNote?.count_plates,
            tempNote?.plates_format,
            tempNote?.count_material,
            tempNote?.count_stock_material,
            tempNote?.remainder_material,
            tempNote?.setup_material,
            tempNote?.inks_print_name,
            tempNote?.alco_print_count,
            tempNote?.pH_print_name,
            tempNote?.pH_print_count
            //tempNote?.count_colors_names  //сюда записать значения для разных ячеек в шаблоне
            //tempNote?.description //описание разных ячеек
            // Добавьте другие значения
        )
        val countColorsNames = tempNote?.count_colors_names?.split("/") ?: emptyList()
        dataValues += countColorsNames
        val descriptionPlus = tempNote?.description?.split("/") ?: emptyList()
        dataValues += descriptionPlus
        val ink_consumption = tempNote?.inks_print_job?.split("/") ?: emptyList()
        dataValues += ink_consumption
        val other_name = tempNote?.other_materials_name?.split("/") ?: emptyList()
        dataValues += other_name
        val other_count = tempNote?.other_materials_count?.split("/") ?: emptyList()
        dataValues += other_count



        val mainDataCoordinates = dataCoordinates.take(dataValues.size)
        val mainDataValues = dataValues.take(mainDataCoordinates.size)

        mainDataCoordinates.forEachIndexed { index, (row, column) ->
            val value = mainDataValues[index]
            val cell = sheet.getRow(row)?.getCell(column.toInt())
            cell?.setCellValue(value)
        }



        dataCoordinates.forEachIndexed { index, (row, column) ->
            val value = dataValues[index]
            val cell = sheet.getRow(row)?.getCell(column.toInt())
            cell?.setCellValue(value)
        }

        return workbook
    }
    private fun convertToNumericCoordinates(coordinates: String): Pair<Int, Short> {
        val column = CellReference(coordinates).col
        val row = CellReference(coordinates).row
        return Pair(row, column)
    }


    override fun shareOfsetNoteItem(note: List<OffsetNotes>) {
        val tempNote = note.firstOrNull() // Передайте только первую запись, так как функция makeShareText ожидает List<OffsetNotes>
        val workbook = fillXLSMFile(tempNote)

        val cacheDir = requireContext().cacheDir
        val fileName = tempNote?.name ?: "" // Получите имя файла из объекта OffsetNotes
        val tempFile = File(cacheDir, "$fileName.xlsm")

        tempFile.outputStream().use { outputStream ->
            workbook.write(outputStream)
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(requireContext(), "com.roman_druck.ampg_printing_room.fileprovider", tempFile))
        }
        val chooserIntent = Intent.createChooser(
            intent, getString(R.string.crime_report)

        )
        startActivity(chooserIntent)


    }



    companion object {
        const val NEW_NOTE_OFFSET_KEY = "new_note_offset_key"
        const val EDIT_STATE_KEY = "edit_state_key"

        @JvmStatic
        fun newInstance() = OffsetNoteFragment()

    }

}