package com.maku.interviewandroidtest.common.repo

import com.maku.interviewandroidtest.common.data.local.datasource.LocalDataSource

interface IProductRepo {
    val local: LocalDataSource
}