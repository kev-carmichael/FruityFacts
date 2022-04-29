package com.example.fruityfacts.data

class FruitFavourites {

    //val fruitFavouritesList: MutableList<String> = ArrayList()
    companion object{
        val fruitFavouritesList: MutableList<String> = ArrayList()
        fun getList(): List<String>{
            return fruitFavouritesList
        }
        fun addToList(fruit: String){
            fruitFavouritesList.add(fruit)
        }
    }

    /*fun getList(): List<String>{
        return fruitFavouritesList
    }

    fun addToList(fruit: String){
        fruitFavouritesList.add(fruit)
    }*/

}