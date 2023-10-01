package com.example.recipes.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.data.entity.Recipes
import com.example.recipes.databinding.HomeCardBinding
import com.example.recipes.ui.viewmodel.MainPageViewModel

class RecipesAdapter(
    var recipesList: List<Recipes>,
    var viewModel: MainPageViewModel
) : RecyclerView.Adapter<RecipesAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(val binding: HomeCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {

        val view = HomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CardDesignHolder(view)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val recipe = recipesList[position]
        holder.binding.textViewName.text = recipe.name
        //holder.binding.textViewDetail.text = recipe.description

        holder.binding.goToDetail.setOnClickListener {
            Navigation.findNavController(holder.itemView).navigate(
                R.id.recipeDetailFragment, bundleOf(
                    "recipeId" to recipe.id
                )
            )
        }
    }
}