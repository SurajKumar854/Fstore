package com.kyubi.retrofitexample.repository

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyubi.retrofitexample.db.ProductDB
import com.kyubi.retrofitexample.helpers.UserHelper
import com.kyubi.retrofitexample.models.Products
import com.kyubi.retrofitexample.retrofit.RetrofitHelper
import com.kyubi.retrofitexample.utlis.NetworkUtlis

class ProductRepository(
    private val product_service: RetrofitHelper,
    private val productDB: ProductDB, private val context: Context
) {
    private val productLiveData = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>>
        get() = productLiveData

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getProductList() {
        if (NetworkUtlis.isOnline(context)) {
            val result = product_service.getInstance().getProductList()
            if (result?.body() != null) {
                Log.e("results",result.body().toString())
                productDB.productDao().addProducts(result.body()!!)
                productLiveData.postValue(result.body())
            }
        } else {
            val products = productDB.productDao().getProducts()
            productLiveData.postValue(products!!)
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

    }
}