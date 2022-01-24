package com.maku.interviewandroidtest.common.data.interfaces

import kotlinx.coroutines.flow.Flow
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity

interface ProductDataSource {
    fun readProducts(): Flow<List<AddItemEntity>>

    suspend fun addProduct(addItemEntity: AddItemEntity)
}