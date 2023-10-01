package com.example.recipes.retrofit

class ApiUtils {

    companion object {
        val BASE_URL = "https://api.canerture.com/recipes/"

        fun getRecipeDao(): RecipeKdao {
            return RetrofitClient.getClient(BASE_URL).create(RecipeKdao::class.java)
        }
    }
}