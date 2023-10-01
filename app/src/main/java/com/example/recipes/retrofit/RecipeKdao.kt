package com.example.recipes.retrofit

import com.example.recipes.data.entity.CRUD
import com.example.recipes.data.entity.RecipeAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecipeKdao {
    //https://api.canerture.com/recipes/get_recipes.php

    @GET("get_recipes.php")
    fun allRecipes():Call<RecipeAnswer>

    @POST("search_recipe.php")
    @FormUrlEncoded
    fun recipeSearch(@Field("recipe_name") recipe_name: String):Call<RecipeAnswer>

    @GET("get_recipe_detail.php")
    fun recipeDetail(@Query("recipe_id") recipe_id: Int): Call <RecipeAnswer>

    @POST("add_recipe.php")
    @FormUrlEncoded
    fun recipeAdd(@Field("recipe_name") recipe_name: String,
                  @Field("recipe_detail") recipe_detail: String) : Call<CRUD>

    @POST("update_recipe.php")
    @FormUrlEncoded
    fun recipeUpdate(@Field("recipe_id")recipe_id: Int,
                     @Field("recipe_name")recipe_name: String,
                     @Field("recipe_detail")recipe_detail: String) : Call<CRUD>

}