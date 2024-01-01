package com.roman_druck.ampg_printing_room.db


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roman_druck.ampg_printing_room.R
import com.roman_druck.ampg_printing_room.databinding.OffsetNoteListItemBinding
import com.roman_druck.ampg_printing_room.entities.OffsetNotes

class OffsetNoteAdapter(private val listener: Listener): ListAdapter<OffsetNotes, OffsetNoteAdapter.ItemHolder>(ItemComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }
    class ItemHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = OffsetNoteListItemBinding.bind(view)
        fun setData(note: OffsetNotes, listener: Listener) = with(binding){
            tvDate.text = note.date
            tvName.text = note.name
            tvNumber.text = note.number
            tvMachine.text = note.machine
            tvMaterial.text = note.material
            tvPaperFormat.text = note.paper_format
            tvCountSheets.text = note.count_sheets
            tvThickness.text = note.paper_thickness
            tvDensity.text = note.paper_density
            tvCountPlates.text = note.count_plates
            tvPlatesFormat.text = note.plates_format
            tvCountColorsNames.text = note.count_colors_names
            tvDescription.text = note.description
            //imSimple.tag = note.photoFileName
            tvCountMaterial.text = note.count_material
            tvCountStockMaterial.text = note.count_stock_material
            tvRemainderMaterial.text = note.remainder_material
            tvSetupMaterial.text = note.setup_material
            tvInksName.text = note.inks_print_name
            tvInksJob.text = note.inks_print_job
            tvAlcoCount.text = note.alco_print_count
            tvPHname.text = note.pH_print_name
            tvPHCount.text = note.pH_print_count
            tvOtherName.text = note.other_materials_name
            tvOtherCount.text = note.other_materials_count
            itemView.setOnClickListener {
                listener.onClickItem(note)
            }
            imDelete.setOnClickListener {
                listener.deleteItem(note.id!!)
            }
            shearReport.setOnClickListener{
                listener.shareOfsetNoteItem(listOf(note))
            }



        }
        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(
                             LayoutInflater.from(parent.context)
                            .inflate(R.layout.offset_note_list_item, parent, false))
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<OffsetNotes>(){
        override fun areItemsTheSame(oldItem: OffsetNotes, newItem: OffsetNotes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OffsetNotes, newItem: OffsetNotes): Boolean {
            return oldItem == newItem
        }

    }
    interface Listener{
        fun deleteItem(id: Int)
        fun onClickItem(note: OffsetNotes)
        fun shareOfsetNoteItem(note: List<OffsetNotes>)

    }


}


