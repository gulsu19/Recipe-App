package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeRequest(

    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
) : Serializable