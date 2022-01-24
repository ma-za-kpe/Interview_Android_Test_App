package com.maku.interviewandroidtest.common.data.local.db.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity

class ProductConvertor {
    var gson = Gson()

    @TypeConverter
    fun productsToString(resultItem: List<AddItemEntity>): String{
        return gson.toJson(resultItem)
    }
    @TypeConverter
    fun stringToProducts(data: String): List<AddItemEntity>{
        val listType = object: TypeToken<List<AddItemEntity>>(){}.type
        return gson.fromJson(data, listType)
    }
}