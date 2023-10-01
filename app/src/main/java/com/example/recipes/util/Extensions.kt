package com.example.recipes.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.makeTransition(id:Int,it:View){
    findNavController(it).navigate(id)
}

fun Navigation.makeTransition(it:View, id:NavDirections){
    findNavController(it).navigate(id)
}
