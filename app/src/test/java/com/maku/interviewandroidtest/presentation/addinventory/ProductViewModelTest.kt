package com.maku.interviewandroidtest.presentation.addinventory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maku.interviewandroidtest.common.data.local.datasource.LocalDataSource
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.repo.ProductRepo
import com.maku.interviewandroidtest.getOrAwaitValue
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

// @RunWith(AndroidJUnit4::class)
class ProductViewModelTest : TestCase(){

//    private val product1 = AddItemEntity(
//        "momo", "Description1","machines", "Description1",
//        "Title1", "Description1", true, 1,
//        33, 45, 12, "img")
//    private val product2 = AddItemEntity(
//        "Title1", "Description1","Title1", "Description1",
//        "Title1", "Description1", true, 1,
//        33, 45, 12, "img")
//    private val product3 = AddItemEntity(
//        "Title1", "Description1","Title1", "Description1",
//        "Title1", "Description1", true, 1,
//        33, 45, 12, "img")
//
//    private val localTasks = listOf(product1, product2, product3).sortedBy { it.id }
//
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var productViewModel: ProductViewModel
//
//    @Mock
//    lateinit var repository: ProductRepo
//    lateinit var localDataSource: LocalDataSource
//
//    @Before
//    suspend fun setup() {
//        repository = ProductRepo(localDataSource)
//        repository.local.addProduct(product1)
//        productViewModel = ProductViewModel(repository, ApplicationProvider.getApplicationContext())
//    }
//
//    @Test
//    fun getProducts_requestsAllProductsFromLocalDataSource(){
//        // When tasks are requested from the tasks repository
//        val products = repository.local.readProducts()
//
//        // Then tasks are loaded from the local data source
//        assertThat(products, IsEqual(localTasks))
//    }
//
//    @Test
//    fun addNewProduct_setsNewProductEvent() {
//        // When adding a new task
//        productViewModel.insertProduct(product1)
//        productViewModel.productsResponse
//
//        val result = productViewModel.readProducts.getOrAwaitValue().find {
//            it.pdt_name == "momo" && it.category == "machines"
//        }
//
//        // Then
//        // assertThat(result.toString() != null).isTrue()
//        assertThat(result?.pdt_name, (not(nullValue())))
//
//    }

    @Test
    fun sampleTest(){
        Assert.assertEquals(4, 2+2)
    }

}
