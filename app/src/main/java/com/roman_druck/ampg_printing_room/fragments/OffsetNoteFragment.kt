package com.roman_druck.ampg_printing_room.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.activities.MainApp
import com.roman_druck.ampg_printing_room.activities.NewOffsetNotesActivity
import com.roman_druck.ampg_printing_room.databinding.FragmentOffsetNoteBinding
import com.roman_druck.ampg_printing_room.db.MainViewModel
import com.roman_druck.ampg_printing_room.db.OffsetNoteAdapter
import com.roman_druck.ampg_printing_room.entities.OffsetNotes
import com.roman_druck.ampg_printing_room.utils.ShareHelperManager


class OffsetNoteFragment : BaseFragment(), OffsetNoteAdapter.Listener {
    private lateinit var binding: FragmentOffsetNoteBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: OffsetNoteAdapter
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
        mainViewModel.deleteNote(id)
    }

    override fun onClickItem(note: OffsetNotes) {
        val intent = Intent(activity, NewOffsetNotesActivity::class.java).apply {
            putExtra(NEW_NOTE_OFFSET_KEY, note)
        }
        editLauncher.launch(intent)
    }

    override fun shareOfsetNoteItem(note: List<OffsetNotes>) {
        val reportIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plane"
            putExtra(Intent.EXTRA_TEXT, makeShareText(note))

        }
        val chooserIntent = Intent.createChooser(
            reportIntent, getString(R.string.crime_report)

        )
        startActivity(chooserIntent)


    }

    private fun makeShareText(note: List<OffsetNotes>): String {
        val sBuilder = StringBuilder()
        var counter = 0
        note.forEach {
            //sBuilder.append("${counter++}")
            sBuilder.append("Дата:${it.date} ")
            sBuilder.append("\n")
            sBuilder.append("\n")
            sBuilder.append(" ${it.name} ")
            sBuilder.append("\n")
            sBuilder.append("${it.number} ")
            sBuilder.append("\n")
            sBuilder.append("${it.machine} ")
            sBuilder.append("\n")
            sBuilder.append("\n")
            sBuilder.append("${it.material} ")
            sBuilder.append("\n")
            sBuilder.append("${it.paper_format} ")
            sBuilder.append("\n")
            sBuilder.append("${it.count_sheets} ")
            sBuilder.append("\n")
            sBuilder.append("${it.paper_thickness} ")
            sBuilder.append("\n")
            sBuilder.append("${it.paper_density} ")
            sBuilder.append("\n")
            sBuilder.append("${it.count_plates} ")
            sBuilder.append("\n")
            sBuilder.append("${it.plates_format} ")
            sBuilder.append("\n")
            sBuilder.append("${it.count_colors_names} ")
            sBuilder.append("\n")
            sBuilder.append("${it.description} ")
            sBuilder.append("\n")
            sBuilder.append("${it.count_material} ")
            sBuilder.append("\n")
            sBuilder.append("${it.count_stock_material} ")
            sBuilder.append("\n")
            sBuilder.append("${it.remainder_material} ")
            sBuilder.append("\n")
            sBuilder.append("${it.setup_material} ")
            sBuilder.append("\n")
            sBuilder.append("${it.inks_print_name} ")
            sBuilder.append("\n")
            sBuilder.append("${it.inks_print_job} ")
            sBuilder.append("\n")
            sBuilder.append("${it.alco_print_count} ")
            sBuilder.append("\n")
            sBuilder.append("${it.pH_print_name} ")
            sBuilder.append("\n")
            sBuilder.append("${it.pH_print_count} ")
            sBuilder.append("\n")
            sBuilder.append("${it.other_materials_name} ")
            sBuilder.append("\n")
            sBuilder.append("\n")
            sBuilder.append("${it.other_materials_count} ")
            sBuilder.append("\n")

        }
        return sBuilder.toString()
    }






    companion object {
        const val NEW_NOTE_OFFSET_KEY = "new_note_offset_key"
        const val EDIT_STATE_KEY = "edit_state_key"

        @JvmStatic
        fun newInstance() = OffsetNoteFragment()

    }

}