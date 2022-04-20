package com.example.fruityfacts.data


import com.google.gson.annotations.SerializedName

data class Fruit(
    val genus: String,
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val nutritions: Nutritions
) {
    data class Nutritions(
        val carbohydrates: Double,
        val protein: Double,
        val fat: Double,
        val calories: Int,
        val sugar: Double
    )
}