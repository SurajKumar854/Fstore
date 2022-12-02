package com.kyubi.retrofitexample.repository

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyubi.retrofitexample.db.ProductDB
import com.kyubi.retrofitexample.models.Products
import com.kyubi.retrofitexample.retrofit.RetrofitHelper
import com.kyubi.retrofitexample.utlis.NetworkUtlis

class ProductRepository(
    private val product_service: RetrofitHelper,
    private val productDB: ProductDB, private val context: Context
) {
    // without response handling
/*    private val productLiveData = MutableLiveData<List<Products>>()*/
    private val productLiveData = MutableLiveData<Response<List<Products>>>()
    val products: LiveData<Response<List<Products>>>
        get() = productLiveData

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getProductList() {
        if (NetworkUtlis.isOnline(context)) {

            try {
                productLiveData.postValue(Response.Loading())
                val result = product_service.getInstance().getProductList()
                if (result?.body() != null) {
                    Log.e("results", result.body().toString())
                    productDB.productDao().addProducts(result.body()!!)
                    productLiveData.postValue(Response.Success(result.body()!!))
                } else {
                    productLiveData.postValue(Response.Error("Error has been occurred.. Please check your server"))
                }
            } catch (e: java.lang.Exception) {
                productLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val products = productDB.productDao().getProducts()
            productLiveData.postValue(Response.Success(products))
            Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getProductsBackground() {
        if (NetworkUtlis.isOnline(context)) {
            val result = product_service.getInstance().getProductList()
            if (result?.body() != null) {
                Log.e("results", result.body().toString())
                productDB.productDao().addProducts(result.body()!!)
                productLiveData.postValue(Response.Success(result.body()!!))
            }
        } else {
            val products = productDB.productDao().getProducts()
            productLiveData.postValue(Response.Success(products!!))
            //Toast.makeText(context, "Please check your internet", Toast.LENGTH_LONG).show()
        }

    }
}