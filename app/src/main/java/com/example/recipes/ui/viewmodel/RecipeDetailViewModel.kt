package com.example.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recipes.data.repo.RecipeKdaoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor (private val kdao : RecipeKdaoRepo): ViewModel() {

    fun recipeDetail(recipe_id: Int){
        kdao.recipeDetail(recipe_id)
    }

    fun recipeUpdate(recipe_id: Int, recipe_name: String, recipe_detail: String) {
        kdao.recipeUpdate(recipe_id,recipe_name,recipe_detail)
    }
}