package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName

data class BaseRecipes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
)