package com.maku.interviewandroidtest.presentation.addinventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.repo.ProductRepo
import com.maku.interviewandroidtest.common.utils.HandleResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor (val repo: ProductRepo, application: Application):
    AndroidViewModel(application) {

    // read from db
    val readProducts = repo.local.readProducts().asLiveData()

    // add to db
    var productsResponse: MutableLiveData<HandleResult<AddItemEntity>> = MutableLiveData()
    // TODO: ideally overkill for such a simple app, but sends the point across for network
    //  connected application
    fun insertProduct(addItemEntity: AddItemEntity) = viewModelScope.launch(Dispatchers.IO){
        productsResponse.postValue(HandleResult.Loading())
        // this is where you can all check for internet connection in small apps
        try {
            repo.local.addProduct(addItemEntity)
            productsResponse.postValue(HandleResult.RoomSuccess("Successfully Added!"))
            // here you can also handle responses from the server in a bigger application
        } catch (e: Exception) {
            productsResponse.postValue(HandleResult.Error(e.toString()))
        }
    }

}

