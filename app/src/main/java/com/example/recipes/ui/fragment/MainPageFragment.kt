package com.example.recipes.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentMainPageBinding
import com.example.recipes.ui.adapter.RecipesAdapter
import com.example.recipes.ui.viewmodel.MainPageViewModel
import com.example.recipes.util.makeTransition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentMainPageBinding
    private val viewModel: MainPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL)

        viewModel.getRecipes()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain)

        viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipes?.let {
                val adapter = RecipesAdapter(it.recipes, viewModel)
                binding.rv.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        observeSearchedFoods()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.tb_menu_act, menu)
                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@MainPageFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.recipeEntryFragment)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchFood(query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.searchFood(newText.toString())
        return true
    }

    private fun observeSearchedFoods() {
        viewModel.recipeSearch.observe(viewLifecycleOwner) { recipes ->
            recipes?.let {
                val adapter = RecipesAdapter(it.recipes, viewModel)
                binding.rv.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

}
