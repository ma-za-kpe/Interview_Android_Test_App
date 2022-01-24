package com.maku.interviewandroidtest.common.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.data.local.db.entities.readProduct.ResultItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(addItemEntity: AddItemEntity)

    @Query("SELECT * FROM products ORDER BY id ASC")
    fun readProduct(): Flow<List<AddItemEntity>>
}