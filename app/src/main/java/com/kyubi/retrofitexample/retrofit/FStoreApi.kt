package com.kyubi.retrofitexample.retrofit

import com.kyubi.retrofitexample.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface FStoreApi {

    @GET("products")
    suspend fun getProductList(): Response<List<Products>>

    @GET("products/1")
    suspend fun getProduct(): Response<Products>


}