package com.example.recipes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.data.entity.Recipes
import com.example.recipes.databinding.DetailCardDesignBinding
import com.example.recipes.ui.fragment.MainPageFragmentDirections
import com.example.recipes.ui.viewmodel.MainPageViewModel
import com.example.recipes.util.makeTransition

class RecipesAdapter (var mContext: Context,
                      var recipesList: List<Recipes>,
                      var viewModel: MainPageViewModel)
    : RecyclerView.Adapter<RecipesAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(binding: DetailCardDesignBinding) : RecyclerView.ViewHolder(binding.root){
        var binding: DetailCardDesignBinding
        init {
            this.binding = binding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: DetailCardDesignBinding = DataBindingUtil.inflate(layoutInflater, R.layout.detail_card_design,parent,false)
        return CardDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val recipe = recipesList[position]

        holder.binding.recipeObject = recipe

        holder.binding.designCard.setOnClickListener {
            val transition = MainPageFragmentDirections.recipeDetailFragment(recipe = recipe)
            val navController = Navigation.findNavController(it)
            navController.navigate(transition)
        }
    }
}