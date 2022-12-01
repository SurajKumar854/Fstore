package com.kyubi.retrofitexample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kyubi.retrofitexample.models.Products

@Dao
interface ProductDao {
    @Insert
    suspend fun addProducts(products: List<Products>)

    @Query("Select * from products")
    suspend fun getProducts(): List<Products>
}