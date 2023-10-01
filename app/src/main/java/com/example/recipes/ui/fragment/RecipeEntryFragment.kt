package com.example.recipes.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeEntryBinding
import com.example.recipes.ui.viewmodel.RecipeDetailViewModel
import com.example.recipes.ui.viewmodel.RecipeEntryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeEntryFragment : Fragment() {
    private lateinit var binding: FragmentRecipeEntryBinding
    private val viewModel: RecipeEntryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipe_entry,container,false)

        binding.recipeEntryToolbarTittle = "New Recipe"
        binding.recipeEntryFragment = this

        return binding.root
    }

    fun buttonSave(recipe_name:String, recipe_detail:String) {
        viewModel.save(recipe_name,recipe_detail)
    }

}