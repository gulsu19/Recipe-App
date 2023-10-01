package com.example.recipes.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeEntryBinding
import com.example.recipes.ui.viewmodel.RecipeEntryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeEntryFragment : Fragment() {
    private lateinit var binding: FragmentRecipeEntryBinding
    private val viewModel: RecipeEntryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRecipeEntryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextRecipeName.text.toString()
            val description = binding.editTextRecipeDetail.text.toString()

            viewModel.addRecipe(name, description)
        }

        viewModel.addRecipe.observe(viewLifecycleOwner) {
            if(it != null){
                Toast.makeText(requireContext(), "Recipe added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_recipeEntryFragment_to_mainPageFragment)
            } else{
                Toast.makeText(requireContext(), "Recipe NOT added", Toast.LENGTH_SHORT).show()
            }
        }
    }
}