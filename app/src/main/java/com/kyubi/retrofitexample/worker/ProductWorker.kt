package com.kyubi.retrofitexample.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kyubi.retrofitexample.FStoreApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun doWork(): Result {

        Log.e("background", "started")
        val repository = (context as FStoreApplication).fstoreProductRepository

        CoroutineScope(Dispatchers.IO).launch {
            repository.getProductsBackground()
        }
        return Result.success()
    }
}