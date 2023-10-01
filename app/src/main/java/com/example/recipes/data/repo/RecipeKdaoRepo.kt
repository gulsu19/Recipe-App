package com.example.recipes.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.recipes.data.entity.CRUD
import com.example.recipes.data.entity.RecipeAnswer
import com.example.recipes.data.entity.Recipes
import com.example.recipes.retrofit.RecipeKdao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RecipeKdaoRepo @Inject constructor(private val kdao: RecipeKdao) {

    var recipesList: MutableLiveData<List<Recipes>> = MutableLiveData()

    init {
        recipesList = MutableLiveData()
    }

    fun getRecipes(): MutableLiveData<List<Recipes>>{
        return recipesList
    }

    fun recipeSave(recipe_name:String, recipe_detail:String) {
        kdao.recipeAdd(recipe_name,recipe_detail).enqueue(object : Callback<CRUD>{
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val status = response.body()?.status
                val message = response.body()?.message
                Log.e("Recipe Saved","$status -$message")
                getAllRecipes()
            }

            override fun onFailure(call: Call<CRUD>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun recipeUpdate(recipe_id: Int, recipe_name: String, recipe_detail: String){
        kdao.recipeUpdate(recipe_id,recipe_name,recipe_detail).enqueue(object : Callback<CRUD>{
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val status = response.body()?.status
                val message = response.body()?.message
                Log.e("Recipe Update" ,"$status - $message")
                getAllRecipes()
            }

            override fun onFailure(call: Call<CRUD>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun recipeDetail(recipe_id: Int){
        kdao.recipeDetail(recipe_id).enqueue(object : Callback<RecipeAnswer>{
            override fun onResponse(
                call: Call<RecipeAnswer>,
                response: Response<RecipeAnswer>
            ) {
                val status = response.body()!!.status
                val message = response.body()!!.message
                Log.e("Recipe Detail","$status - $message")
            }
            override fun onFailure(call: Call<RecipeAnswer>, t: Throwable) {
            }

        })
    }

    fun recipeSearch(searchWord:String){
        kdao.recipeSearch(searchWord).enqueue(object : Callback<RecipeAnswer>{
            override fun onResponse(call: Call<RecipeAnswer>, response: Response<RecipeAnswer>) {
                val list = response.body()!!.recipes
                recipesList.value = list
            }

            override fun onFailure(call: Call<RecipeAnswer>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getAllRecipes(){
        kdao.allRecipes().enqueue(object : Callback<RecipeAnswer>{
            override fun onResponse(call: Call<RecipeAnswer>?, response: Response<RecipeAnswer>) {
                val list = response.body()!!.recipes
                recipesList.value = list
            }

            override fun onFailure(call: Call<RecipeAnswer>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}