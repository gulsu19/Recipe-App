package com.example.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recipes.data.repo.RecipeKdaoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeEntryViewModel @Inject constructor(private val kdao : RecipeKdaoRepo): ViewModel() {
    fun save(recipe_name:String, recipe_detail:String){
        kdao.recipeSave(recipe_name,recipe_detail)
    }
}