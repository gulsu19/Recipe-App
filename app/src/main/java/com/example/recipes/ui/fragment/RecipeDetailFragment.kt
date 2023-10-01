package com.example.recipes.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeDetailBinding
import com.example.recipes.ui.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    private val viewModel: RecipeDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_detail, container, false)
        binding.recipeDetailFragment = this
        binding.recipeDetailToolbarTitle = "Recipes Detail"

        val bundle: RecipeDetailFragmentArgs by navArgs()
        val comingRecipe = bundle.recipe
        binding.recipeObject = comingRecipe

        comingRecipe?.let {
            binding.recipeObject = it
        }

        return binding.root
    }

    fun buttonUpdate(recipe_id: Int , recipe_name: String, recipe_detail: String){
        viewModel.recipeUpdate(recipe_id,recipe_name,recipe_detail)
    }
}