package com.elanyudho.pokemon.search

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.core.util.extension.getIdFromUrl
import com.elanyudho.core.util.pagination.RecyclerViewPaginator
import com.elanyudho.pokemon.R
import com.elanyudho.pokemon.databinding.ActivitySearchBinding
import com.elanyudho.pokemon.ui.detail.DetailPokemonActivity
import com.elanyudho.pokemon.ui.main.MainActivity
import com.elanyudho.pokemon.ui.main.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : BaseActivityBinding<ActivitySearchBinding>(), Observer<SearchViewModel.SearchUiState> {

    @Inject
    lateinit var searchViewModel: SearchViewModel

    private var paginator: RecyclerViewPaginator? = null

    private val pokemonAdapter: PokemonAdapter by lazy { PokemonAdapter() }

    private lateinit var query: String

    private var isFirstGet = true

    private lateinit var currSort: String

    override val bindingInflater: (LayoutInflater) -> ActivitySearchBinding
        get() = { ActivitySearchBinding.inflate(layoutInflater) }

    override fun setupView() {
        getDataIntent()
        initViewModel()
        setAction()
        setAdapter()
        setRvPagination()
    }

    override fun onChanged(state: SearchViewModel.SearchUiState?) {
        when(state) {
            SearchViewModel.SearchUiState.InitialLoading -> {
                initialLoading()
            }
            SearchViewModel.SearchUiState.PagingLoading -> {
                pagingLoading()
            }
            is SearchViewModel.SearchUiState.PokemonsLoaded -> {
                stopLoading()
                if (state.data.isEmpty() && isFirstGet) {
                    showEmptyData()
                }else {
                    hideEmptyData()
                    pokemonAdapter.appendList(state.data)
                }
            }
            else -> {}
        }
    }

    private fun getDataIntent() {
        query =  intent.getStringExtra("query").toString()
        currSort = intent.getStringExtra("sort").toString()

        binding.svPokemon.setQuery(query)
    }

    private fun initViewModel() {
        searchViewModel.uiState.observe(this, this)
        searchViewModel.getPokemonByName(query, currSort, 1L)
    }

    private fun setAdapter() {
        with(binding.rvPokemon) {
            adapter = pokemonAdapter
            setHasFixedSize(true)

            pokemonAdapter.setOnClickData {
                hideKeyboard(binding.svPokemon)
                val intent = Intent(this@SearchActivity, DetailPokemonActivity::class.java)
                intent.putExtra("pokemonId", it.url.getIdFromUrl())
                startActivity(intent)
            }
        }
    }

    private fun setRvPagination() {
        paginator = RecyclerViewPaginator(binding.rvPokemon.layoutManager as LinearLayoutManager)
        paginator?.setOnLoadMoreListener { page ->
            Log.d("PAGE", page.toString())
            isFirstGet = false
            searchViewModel.getPokemonByName(query, currSort, page)
        }
        paginator?.let { binding.rvPokemon.addOnScrollListener(it) }
    }

    private fun setAction() {
        with(binding) {
            btnBack.setOnClickListener { onBackPressed() }

            svPokemon.setOnQueryChangeListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.toString().isNotEmpty()) {
                         this@SearchActivity.query= query!!
                        isFirstGet = true
                        pokemonAdapter.clearList()
                        searchViewModel.getPokemonByName(this@SearchActivity.query, currSort, 1L)
                        paginator = null
                        setRvPagination()
                    }
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

    private fun showFilterDialog() {
        val filterOptions = arrayOf(getString(R.string.asc_sort), getString(R.string.desc_sort))
        var sort = currSort
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.sort_by))
            .setSingleChoiceItems(filterOptions, if (sort == MainActivity.SORT_ASC) 0 else 1) { _, which ->
                when (which) {
                    0 -> {
                        sort = MainActivity.SORT_ASC
                    }
                    1 -> {
                        sort = MainActivity.SORT_DESC
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

}