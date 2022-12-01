package com.kyubi.retrofitexample.retrofit

import retrofit2.Response
import retrofit2.http.Body

object ServiceCallback {
    interface data {
        suspend fun success()
        suspend fun fail()
    }
}