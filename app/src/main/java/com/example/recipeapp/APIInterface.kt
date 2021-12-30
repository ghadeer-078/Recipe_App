package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIInterface {

    @GET("/recipes/")
    fun getRecipe(): Call<List<RecipeDetailsItem>>

    @POST("/recipes/")
    fun addRecipe(@Body userData: RecipeDetailsItem): Call<RecipeDetailsItem>

}