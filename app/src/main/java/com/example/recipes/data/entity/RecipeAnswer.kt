package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipesAnswer(
    @SerializedName("recipes")
    val recipes: List<Recipes>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
) : Serializable