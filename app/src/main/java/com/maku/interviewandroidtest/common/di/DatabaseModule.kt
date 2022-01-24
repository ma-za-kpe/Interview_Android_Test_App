package com.maku.interviewandroidtest.common.di

import android.content.Context
import androidx.room.Room
import com.maku.interviewandroidtest.common.data.local.db.ProductDatabase
import com.maku.interviewandroidtest.common.utils.Constants.Companion.INTERVIEW_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        INTERVIEW_DB
    ).build()

    @Singleton
    @Provides
     fun provideDao(database: ProductDatabase) = database.productDao()

}