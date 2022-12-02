package com.kyubi.retrofitexample.repository

import com.kyubi.retrofitexample.models.Products

/*
sealed class Response<T>() {
    class Loading<T> : Response<T>()
    class Success<T>(val products: T) : Response<T>()
    class Error<T>(val errorMessage: String) : Response<T>()
}*/

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}