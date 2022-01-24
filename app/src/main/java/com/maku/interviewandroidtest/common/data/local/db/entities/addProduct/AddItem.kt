package com.maku.interviewandroidtest.common.data.local.db.entities.addProduct

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.interviewandroidtest.common.utils.Constants

@Entity(tableName = Constants.ADD_PRODUCT_TABLE)
data class AddItemEntity (
    val pdt_name: String,
    val pdt_code: String,
    val category: String,
    val product_type: String,
    val manufacturer: String,
    val distributor: String,
    val vat: Boolean,
    val unit_cost: Int,
    val retail_price: Int,
    val agent_price: Int,
    val wholesale_price: Int,
    val pdt_image: String
    )
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}