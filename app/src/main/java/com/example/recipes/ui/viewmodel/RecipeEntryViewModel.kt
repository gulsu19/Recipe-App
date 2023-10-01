package com.example.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.entity.BaseRecipes
import com.example.recipes.data.entity.RecipeRequest
import com.example.recipes.data.repo.RecipeKdaoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeEntryViewModel @Inject constructor(private val kdao : RecipeKdaoRepo): ViewModel() {

        val addRecipe = MutableLiveData<BaseRecipes>()

        fun addRecipe(name: String, description: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val response = kdao.addRecipe(RecipeRequest(name, description))
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        addRecipe.value = response.body()
                    }
                }
            }
        }
    }
