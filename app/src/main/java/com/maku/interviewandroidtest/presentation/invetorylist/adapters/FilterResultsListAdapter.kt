package com.maku.interviewandroidtest.presentation.invetorylist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.maku.interviewandroidtest.R
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.data.local.db.entities.readProduct.ResultItemEntity
import com.maku.interviewandroidtest.common.data.local.db.entities.readProduct.ResultItem
import com.maku.interviewandroidtest.common.utils.InventoryDiffUtil
import com.maku.interviewandroidtest.databinding.FilterResultItemsBinding

class FilterResultsListAdapter internal constructor(
    private val onClick: (Any) -> Unit
)  : RecyclerView.Adapter<FilterResultsListAdapter.ListViewHolder>(){

    private var items = emptyList<AddItemEntity>()

    class ListViewHolder(private val binding: FilterResultItemsBinding): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun setRecentCocktailItem(result: AddItemEntity, onClick: (Any) -> Unit){
            binding.result = result

            binding.microbes.text = result.category
            binding.textView4.text = result.pdt_name
            binding.results.setOnClickListener {
                onClick(result)
            }

            binding.imageView.load(result.pdt_image) {
                crossfade(true)
                placeholder(R.drawable.ic_error_placeholder)
                transformations(CircleCropTransformation())
            }

            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilterResultItemsBinding.inflate(layoutInflater, parent, false)
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

    fun setData(newData: ResultItemEntity){
        val itemsDiffUtil =
            InventoryDiffUtil(items, newData.items)
        val diffUtilResult = DiffUtil.calculateDiff(itemsDiffUtil)
        items = newData.items
        diffUtilResult.dispatchUpdatesTo(this)
    }

}