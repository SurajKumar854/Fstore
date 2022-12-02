package com.kyubi.retrofitexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyubi.retrofitexample.models.Products
import com.kyubi.retrofitexample.repository.ProductRepository
import com.kyubi.retrofitexample.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            Dispatchers.IO
            repository.getProductList()
        }

    }

    val products: LiveData<Response<List<Products>>>
        get() = repository.products
}