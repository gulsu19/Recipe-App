package com.example.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.entity.BaseRecipes
import com.example.recipes.data.entity.DetailResponse
import com.example.recipes.data.entity.Recipes
import com.example.recipes.data.repo.RecipeKdaoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val kdao: RecipeKdaoRepo) : ViewModel() {

    val recipeDetail = MutableLiveData<DetailResponse>()
    val recipeUpdate = MutableLiveData<BaseRecipes>()

    fun recipeDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = kdao.recipeDetail(id)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail.value = response.body()
                }
            }
        }
    }

    fun updateRecipe(recipes: Recipes) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = kdao.recipeUpdate(recipes)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeUpdate.value = response.body()
                }
            }
        }
    }
}