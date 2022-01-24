package com.maku.interviewandroidtest.common.data

import com.maku.interviewandroidtest.common.data.interfaces.ProductDataSource
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource(var products: MutableList<AddItemEntity>? = mutableListOf()): ProductDataSource {
    override fun readProducts(): Flow<List<AddItemEntity>> {
        products?.let { return flow {  } }
        return (flow { Exception("Tasks not found") })
    }

    override suspend fun addProduct(addItemEntity: AddItemEntity) {
        products?.add(addItemEntity)
    }
}