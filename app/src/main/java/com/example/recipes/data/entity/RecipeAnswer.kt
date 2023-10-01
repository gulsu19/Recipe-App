package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName

data class RecipeAnswer (@SerializedName("recipes") var recipes:List<Recipes>,
                         @SerializedName("status") var status:Int,
                         @SerializedName("message") var message:String){
}