package com.example.fruityfacts.api

import com.example.fruityfacts.data.Fruit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitService {
    @GET("{Id}")
    fun getFruit(@Path("Id") Id: String): Call<Fruit>

    @GET("all")
    fun getAllFruit(): List<Fruit>

}