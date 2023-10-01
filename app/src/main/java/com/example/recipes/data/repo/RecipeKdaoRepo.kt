package com.example.recipes.data.repo


import com.example.recipes.data.entity.RecipeRequest
import com.example.recipes.data.entity.Recipes
import com.example.recipes.retrofit.RecipeKdao
import javax.inject.Inject

class RecipeKdaoRepo @Inject constructor(private val kdao: RecipeKdao) {


    suspend fun recipes() = kdao.recipes()
    suspend fun foodSearch(foodName: String) = kdao.foodSearch(foodName)
    suspend fun recipeDetail(id: Int) = kdao.recipeDetail(id)
    suspend fun addRecipe(request: RecipeRequest) = kdao.addRecipe(request)
    suspend fun recipeUpdate(recipe: Recipes) = kdao.recipeUpdate(recipe)

}