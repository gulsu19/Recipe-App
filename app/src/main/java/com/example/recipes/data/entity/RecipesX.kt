package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipesX(
    @SerializedName("recipes")
    var recipes: List<Recipes>,
    @SerializedName("status")
    val status: Int,
) : Serializable {
}