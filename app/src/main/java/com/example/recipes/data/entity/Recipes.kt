package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipes (@SerializedName("recipe_id") var recipe_id: Int?,
                    @SerializedName("recipe_name") var recipe_name: String?,
                    @SerializedName("recipe_detail") var recipe_detail: String?): Serializable {
    override fun hashCode(): Int {
        return recipe_name?.hashCode() ?: 0
    }
}