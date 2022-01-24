package com.maku.interviewandroidtest.common.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maku.interviewandroidtest.common.data.local.db.convertors.ProductConvertor
import com.maku.interviewandroidtest.common.data.local.db.dao.ProductDao
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.data.local.db.entities.readProduct.ResultItemEntity

@Database(
    entities = [AddItemEntity::class],
    version = 1,
    exportSchema = false,
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ]
)
 @TypeConverters(ProductConvertor::class)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}
