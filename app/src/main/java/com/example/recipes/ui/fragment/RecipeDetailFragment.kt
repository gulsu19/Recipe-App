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
import com.example.recipes.data.entity.Recipes
import com.example.recipes.databinding.FragmentRecipeDetailBinding
import com.example.recipes.ui.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    private val viewModel: RecipeDetailViewModel by viewModels()
    private var receivedId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)

        receivedId = arguments?.getInt("recipeId")
        if (receivedId != null) {
            viewModel.recipeDetail(receivedId ?: 0)
        }

        viewModel.recipeDetail.observe(viewLifecycleOwner) {
            binding.editTextRecipeName.setText(it.recipe.name)
            binding.editTextRecipeDetail.setText(it.recipe.description)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdate.setOnClickListener {
            val recipeName = binding.editTextRecipeName.text.toString()
            val recipeDetail = binding.editTextRecipeDetail.text.toString()

            if (receivedId != null) {
                viewModel.updateRecipe(Recipes(receivedId ?: 0, recipeName, recipeDetail))
            }
        }
    }
}