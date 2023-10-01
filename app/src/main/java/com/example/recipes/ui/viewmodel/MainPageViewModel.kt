package com.example.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipes.data.entity.Recipes
import com.example.recipes.data.repo.RecipeKdaoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(private val kdao : RecipeKdaoRepo) : ViewModel(){
    var recipeList = MutableLiveData<List<Recipes>>()

    init {
        uploadRecipes()
        recipeList = kdao.getRecipes()
    }

    fun search(searchWord:String){
        kdao.recipeSearch(searchWord)
    }

    fun uploadRecipes(){
        kdao.getAllRecipes()
    }
}