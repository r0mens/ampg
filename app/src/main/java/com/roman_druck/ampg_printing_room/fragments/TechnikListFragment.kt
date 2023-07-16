package com.roman_druck.ampg_printing_room.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman_druck.ampg_printing_room.activities.MainApp
import com.roman_druck.ampg_printing_room.activities.TechnikListActivity
import com.roman_druck.ampg_printing_room.databinding.FragmentTechnikListBinding
import com.roman_druck.ampg_printing_room.db.MainViewModel
import com.roman_druck.ampg_printing_room.db.TechnikListNameAdapter
import com.roman_druck.ampg_printing_room.dialogs.DeleteDialog
import com.roman_druck.ampg_printing_room.dialogs.NewListDialog
import com.roman_druck.ampg_printing_room.entities.TechnikItem
import com.roman_druck.ampg_printing_room.entities.TechnikState
import com.roman_druck.ampg_printing_room.utils.TimeManager
import kotlinx.coroutines.launch


class TechnikListFragment : BaseFragment(), TechnikListNameAdapter.Listener{
    private lateinit var binding: FragmentTechnikListBinding
    private lateinit var adapter: TechnikListNameAdapter
    private var technikListCallback: TechnikListCallback? = null

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        NewListDialog.showDialog(activity as AppCompatActivity, object : NewListDialog.Listener{
            override fun onClick(name: String) {
                val technikListName = TechnikState(
                    null,
                    name,
                    TimeManager.getCurrentTime(),
                    itemsIds = emptyList()
                )
                mainViewModel.insertTechnikListName(technikListName)
            }
        }, "")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTechnikListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = TechnikListNameAdapter(this@TechnikListFragment)
        rcView.adapter = adapter
    }

    private fun observer() {
        mainViewModel.allTechnikListNames.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    companion object {


        @JvmStatic
        fun newInstance() = TechnikListFragment()

    }

    override fun deleteItem(id: Int) {
        DeleteDialog.showDialog(context as AppCompatActivity, object : DeleteDialog.Listener{
            override fun onClick() {
                mainViewModel.deleteTechnikListName(id)
            }

        })
    }

    override fun updateItem(listname: TechnikState) {
        NewListDialog.showDialog(activity as AppCompatActivity, object : NewListDialog.Listener{
            override fun onClick(name: String) {

                mainViewModel.updateTechnikListName(listname.copy(name_list = name))
            }

        }, listname.name_list)
    }

    override fun onClickItem(listName: TechnikState) {
        lifecycleScope.launch {
            val itemIds = listName.itemsIds ?: emptyList()
            val description = getTechnikItemsDescription(itemIds)
            technikListCallback?.onDescriptionUpdated(description)

            val intent = Intent(requireActivity(), TechnikListActivity::class.java).apply {
                putExtra(TechnikListActivity.LIST_ID_EXTRA, listName.id)
                putExtra(TechnikListActivity.ITEM_IDS_EXTRA, itemIds.toIntArray())
            }
            requireActivity().startActivity(intent)
        }
    }

    private suspend fun getTechnikItemsDescription(itemsIds: List<Int>?): String {
        val items = mutableListOf<TechnikItem>()
        itemsIds?.forEach { itemId ->
            val item = mainViewModel.getTechnikItemById(itemId)
            if (item != null) {
                items.add(item)
            }
        }
        return items.joinToString(", ") { it.description }
    }


    interface TechnikListCallback {
        fun onDescriptionUpdated(description: String)
    }

}