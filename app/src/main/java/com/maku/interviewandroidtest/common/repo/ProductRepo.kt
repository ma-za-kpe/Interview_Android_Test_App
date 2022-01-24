package com.maku.interviewandroidtest.common.repo

import com.maku.interviewandroidtest.common.data.local.datasource.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * This is where we have both remote and local data sources to fee out view models
 * */
@ActivityRetainedScoped
class ProductRepo @Inject constructor(
    localDataSource: LocalDataSource
) : IProductRepo {
    override val local = localDataSource
}