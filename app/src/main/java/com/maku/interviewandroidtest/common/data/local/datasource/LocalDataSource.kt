package com.maku.interviewandroidtest.common.data.local.datasource

import com.maku.interviewandroidtest.common.data.interfaces.ProductDataSource
import com.maku.interviewandroidtest.common.data.local.db.dao.ProductDao
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productDao: ProductDao): ProductDataSource {
    override fun readProducts(): Flow<List<AddItemEntity>> {
        return productDao.readProduct()
    }

    override suspend fun addProduct(addItemEntity: AddItemEntity) {
        productDao.insertProduct(addItemEntity)
    }


//    // loan requests
//    fun readProducts(): Flow<List<AddItemEntity>> {
//        return productDao.readProduct()
//    }
//
//    suspend fun insertProduct(addItemEntity: AddItemEntity) {
//       productDao.insertProduct(addItemEntity)
//    }

}