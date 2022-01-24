package com.maku.interviewandroidtest.common.data.local.db.entities.readProduct

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.utils.Constants

data class ResultItemEntity(
    var items: List<AddItemEntity>
)
