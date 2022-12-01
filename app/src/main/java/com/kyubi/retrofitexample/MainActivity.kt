package com.kyubi.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.kyubi.retrofitexample.helpers.UserHelper
import com.kyubi.retrofitexample.repository.ProductRepository
import com.kyubi.retrofitexample.retrofit.FStoreApi
import com.kyubi.retrofitexample.retrofit.RetrofitHelper
import com.kyubi.retrofitexample.retrofit.ServiceCallback
import com.kyubi.retrofitexample.viewmodels.MainViewModel
import com.kyubi.retrofitexample.viewmodels.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

class MainActivity : AppCompatActivity() {
    var i = 0

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text: TextView = findViewById(R.id.data)

        val repository = (application as FStoreApplication).fstoreProductRepository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        text.text = mainViewModel.products.value?.get(0)?.category
        /*   mainViewModel.products.observe(this) {

               text.text = it[0].category

           }*/

        /* GlobalScope.launch {
         UserHelper.GetProduct(object : ServiceCallback.data{
             override suspend fun success() {
              Log.e("res","success")
             }

             override suspend fun fail() {
                 Log.e("res","fail")
             }

         })
         }*/
        /*   GlobalScope.launch {
               UserHelper.GetProduct()?.category?.let {
                   Log.e("basic data", it)
               }
           }*/

/*

        GlobalScope.launch {
            val result = productList.getProductList()
            if (result.isSuccessful) {
                val products = result.body()
                products?.forEach {
                    val text: TextView = findViewById(R.id.data)
                    Log.e("category", it.category)
                    Log.e("description", it.description)
                    text.setText(it.category + "/n" + it.title)
                }

            }
        }*/
    }


}