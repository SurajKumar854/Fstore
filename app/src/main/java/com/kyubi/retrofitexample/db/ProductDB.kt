package com.kyubi.retrofitexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kyubi.retrofitexample.models.Products

@Database(entities = [Products::class], version = 1)
abstract class ProductDB : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDB? = null


        fun getDatabase(context: Context): ProductDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, ProductDB::class.java, "product_db").build()

                }
            }
            return INSTANCE!!
        }
    }
}