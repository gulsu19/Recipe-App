package com.example.recipes.data.entity

import com.google.gson.annotations.SerializedName

data class CRUD (@SerializedName("status") var status:Int,
                 @SerializedName("message") var message:String) {
}