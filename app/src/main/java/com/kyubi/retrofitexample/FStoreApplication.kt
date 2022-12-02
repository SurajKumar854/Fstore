package com.kyubi.retrofitexample

import android.annotation.SuppressLint
import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.kyubi.retrofitexample.db.ProductDB
import com.kyubi.retrofitexample.repository.ProductRepository
import com.kyubi.retrofitexample.retrofit.RetrofitHelper
import com.kyubi.retrofitexample.worker.ProductWorker
import java.util.concurrent.TimeUnit

class FStoreApplication : Application() {
    lateinit var fstoreProductRepository: ProductRepository
    override fun onCreate() {

        initialize()
        setUpWork()
        super.onCreate()
    }

    private fun initialize() {
        val roomDb = ProductDB.getDatabase(applicationContext)
        fstoreProductRepository = ProductRepository(RetrofitHelper, roomDb, applicationContext)

    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun setUpWork() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(ProductWorker::class.java, 1, TimeUnit.MINUTES)
                .setConstraints(constraint).build()

        WorkManager.getInstance(this).enqueue(workerRequest)
    }
}