package com.maku.interviewandroidtest.common.data.local.db.entities.readProduct

// entity
data class ResultItem (
    val id: Int,
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

// value object: describe some part of an entity
//data class Category(
//    val name: String
//)