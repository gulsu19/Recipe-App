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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentMainPageBinding
import com.example.recipes.ui.adapter.RecipesAdapter
import com.example.recipes.ui.viewmodel.MainPageViewModel
import com.example.recipes.util.makeTransition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding
    private val viewModel: MainPageViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_page,container,false)
        binding.mainPageFragment = this

        binding.toolbarMain.title = "Recipes"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.recipeList?.observe(viewLifecycleOwner) { recipes ->
            recipes?.let {
                val adapter = RecipesAdapter(requireContext(), it, viewModel)
                binding.recipesAdapter = adapter
            }
        }

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.recipeEntryFragment)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.tb_menu_act, menu)
                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        viewModel.search(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        viewModel.search(newText)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    fun fabClick(it: View){
        Navigation.makeTransition(R.id.recipeEntryFragment,it)
    }

    override fun onResume() {
        super.onResume()
        viewModel.uploadRecipes()
    }

}
