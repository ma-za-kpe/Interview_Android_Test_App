package com.maku.interviewandroidtest.presentation.invetorylist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maku.interviewandroidtest.common.data.local.model.FilterItem
import com.maku.interviewandroidtest.common.data.local.model.Item
import com.maku.interviewandroidtest.common.utils.InventoryDiffUtil
import com.maku.interviewandroidtest.databinding.FilterItemsBinding

class FilterListAdapter internal constructor(
    private val onClick: (Any) -> Unit
)  : RecyclerView.Adapter<FilterListAdapter.ListViewHolder>(){

    private var items = emptyList<Item>()

    class ListViewHolder(private val binding: FilterItemsBinding): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun setRecentCocktailItem(result: Item, onClick: (Any) -> Unit){
            binding.result = result

            binding.title.text = result.item
            binding.title.setOnClickListener {
                onClick(result)
                // change color to orange and show text underline

            }

            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilterItemsBinding.inflate(layoutInflater, parent, false)
                return ListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = items[position]
        holder.setRecentCocktailItem(currentItem, onClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newData: FilterItem){
        val itemsDiffUtil =
            InventoryDiffUtil(items, newData.items)
        val diffUtilResult = DiffUtil.calculateDiff(itemsDiffUtil)
        items = newData.items
        diffUtilResult.dispatchUpdatesTo(this)
    }

}