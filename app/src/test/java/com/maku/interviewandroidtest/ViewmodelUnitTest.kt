package com.maku.interviewandroidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maku.interviewandroidtest.common.data.local.datasource.LocalDataSource
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.repo.ProductRepo
import com.maku.interviewandroidtest.presentation.addinventory.ProductViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

//@RunWith(AndroidJUnit4::class)
@RunWith(RobolectricTestRunner::class)
class ViewmodelUnitTest {

        private val product1 = AddItemEntity(
        "momo", "Description1","machines", "Description1",
        "Title1", "Description1", true, 1,
        33, 45, 12, "img")
    private val product2 = AddItemEntity(
        "Title1", "Description1","Title1", "Description1",
        "Title1", "Description1", true, 1,
        33, 45, 12, "img")
    private val product3 = AddItemEntity(
        "Title1", "Description1","Title1", "Description1",
        "Title1", "Description1", true, 1,
        33, 45, 12, "img")

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productViewModel: ProductViewModel


    lateinit var repository: ProductRepo
    lateinit var localDataSource: LocalDataSource

//    @Before
//    fun setup() {
//        localDataSource = LocalDataSource()
//        repository = ProductRepo(localDataSource)
//        productViewModel = ProductViewModel(repository, ApplicationProvider.getApplicationContext())
//    }

    @Test
    fun addNewProduct_setsNewProductEvent() {

        // Given a fresh ViewModel
        // val tasksViewModel = ProductViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        // tasksViewModel.insertProduct(product1)

        // Then the new task event is triggered
        // TODO test LiveData
    }

    @Test
    fun product1_pdtName_isCorrect() {
        assertEquals("momo", product1.pdt_name)
    }
}