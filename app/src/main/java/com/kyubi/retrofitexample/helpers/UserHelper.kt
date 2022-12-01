package com.kyubi.retrofitexample.helpers

import android.util.Log
import com.kyubi.retrofitexample.models.Products
import com.kyubi.retrofitexample.retrofit.FStoreApi
import com.kyubi.retrofitexample.retrofit.RetrofitHelper
import com.kyubi.retrofitexample.retrofit.ServiceCallback

object UserHelper {


    suspend fun GetProducts(): List<Products>? {
        return RetrofitHelper.getInstance().getProductList().body()

    }

    suspend fun GetProduct(servicecallback: ServiceCallback.data) {
        val retrofit = RetrofitHelper.getInstance().getProduct()
        if (retrofit.isSuccessful) {
            servicecallback.success()
        } else {
            servicecallback.fail()
        }


    }
}