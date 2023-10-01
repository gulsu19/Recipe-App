package com.example.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.entity.Recipes
import com.example.recipes.data.entity.RecipesAnswer
import com.example.recipes.data.entity.RecipesX
import com.example.recipes.data.repo.RecipeKdaoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(private val kdao: RecipeKdaoRepo) : ViewModel() {
    val recipeList = MutableLiveData<RecipesAnswer>()

    val recipeSearch = MutableLiveData<RecipesX>()

    fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = kdao.recipes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeList.value = response.body()
                }
            }
        }
    }

    fun searchFood(foodName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = kdao.foodSearch(foodName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeSearch.value = response.body()
                }
            }
        }
    }
}