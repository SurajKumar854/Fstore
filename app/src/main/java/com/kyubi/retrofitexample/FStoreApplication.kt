package com.kyubi.retrofitexample

import android.app.Application
import com.kyubi.retrofitexample.db.ProductDB
import com.kyubi.retrofitexample.helpers.UserHelper
import com.kyubi.retrofitexample.repository.ProductRepository
import com.kyubi.retrofitexample.retrofit.RetrofitHelper

class FStoreApplication : Application() {
    lateinit var fstoreProductRepository: ProductRepository
    override fun onCreate() {

        initialize()
        super.onCreate()
    }

    private fun initialize() {
         val roomDb = ProductDB.getDatabase(applicationContext)
        fstoreProductRepository = ProductRepository(RetrofitHelper, roomDb,applicationContext)

    }
}