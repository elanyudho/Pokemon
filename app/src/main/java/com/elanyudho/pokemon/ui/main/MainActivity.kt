package com.elanyudho.pokemon.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.core.data.local.db.PokemonHelper
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.extension.getIdFromUrl
import com.elanyudho.core.util.pagination.RecyclerViewPaginator
import com.elanyudho.pokemon.R
import com.elanyudho.pokemon.databinding.ActivityMainBinding
import com.elanyudho.pokemon.search.SearchActivity
import com.elanyudho.pokemon.ui.detail.DetailPokemonActivity
import com.elanyudho.pokemon.ui.main.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>(), Observer<MainViewModel.MainUiState> {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var pokemonHelper: PokemonHelper

    private var paginator: RecyclerViewPaginator? = null

    private val pokemonAdapter: PokemonAdapter by lazy { PokemonAdapter() }

    private var isFirstGet = true

    private var currSort = SORT_ASC

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { ActivityMainBinding.inflate(layoutInflater) }

    override fun setupView() {
        initViewModel()
        setAdapter()
        setRvPagination()
        setAction()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard(binding.svPokemon)
        binding.svPokemon.setQuery("")
    }

    override fun onChanged(state: MainViewModel.MainUiState?) {
        when(state) {
            is MainViewModel.MainUiState.InitialLoading -> {
                initialLoading()
            }
            is MainViewModel.MainUiState.PagingLoading -> {
                pagingLoading()
            }
            is MainViewModel.MainUiState.PokemonsLoaded -> {
                stopLoading()
                if (state.data.isEmpty() && isFirstGet) {
                    showEmptyData()
                }else {
                    hideEmptyData()
                    pokemonAdapter.appendList(state.data)
                }
            }
            is MainViewModel.MainUiState.FailedLoaded -> {
                stopLoading()
                handleFailure(state.failure)
            }
            else -> {}
        }
    }

    private fun initViewModel() {
        mainViewModel.uiState.observe(this, this)
        mainViewModel.getPokemons(1L)
    }

    private fun setAdapter() {
        with(binding.rvPokemon) {
            adapter = pokemonAdapter
            setHasFixedSize(true)

            pokemonAdapter.setOnClickData {
                hideKeyboard(binding.svPokemon)
                val intent = Intent(this@MainActivity, DetailPokemonActivity::class.java)
                intent.putExtra("pokemonId", it.url.getIdFromUrl())
                startActivity(intent)
            }
        }
    }

    private fun setRvPagination() {
        paginator = RecyclerViewPaginator(binding.rvPokemon.layoutManager as LinearLayoutManager)
        paginator?.setOnLoadMoreListener { page ->
            isFirstGet = false
            mainViewModel.getPokemons(page)
        }
        paginator?.let { binding.rvPokemon.addOnScrollListener(it) }
    }

    private fun setAction() {
        with(binding) {

            svPokemon.setOnQueryChangeListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val intent = Intent(this@MainActivity, SearchActivity::class.java)
                    intent.putExtra("query", query.toString())
                    intent.putExtra("sort", currSort)
                    startActivity(intent)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }

            })

            svPokemon.setOnAdditionalButtonListener {
                showFilterDialog()
            }
        }
    }

    private fun initialLoading() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.rvPokemon.visibility = View.GONE
    }

    private fun pagingLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.rvPokemon.visibility = View.VISIBLE
        binding.progressCircular.visibility = View.GONE
    }

    private fun showEmptyData() {
        binding.emptyDataView.parent.visibility = View.VISIBLE
    }

    private fun hideEmptyData() {
        binding.emptyDataView.parent.visibility = View.GONE
    }

    private fun handleFailure(failure: Failure) {
        Toast.makeText(this, failure.throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showFilterDialog() {
        val filterOptions = arrayOf(getString(R.string.asc_sort), getString(R.string.desc_sort))
        var sort = currSort
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.sort_by))
            .setSingleChoiceItems(filterOptions, if (sort == SORT_ASC) 0 else 1) { _, which ->
                when (which) {
                    0 -> {
                        sort = SORT_ASC
                    }
                    1 -> {
                        sort = SORT_DESC
                    }
                }
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.apply)) { dialog, _ ->
                // Apply button clicked
                currSort = sort
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onStop() {
        super.onStop()
        pokemonHelper.closeDb()
    }

    companion object {
        const val SORT_ASC = "ASC"
        const val SORT_DESC = "DESC"
    }

}