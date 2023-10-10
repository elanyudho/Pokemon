package com.elanyudho.pokemon.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.core.data.local.db.PokemonHelper
import com.elanyudho.core.domain.model.DetailPokemon
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.extension.glide
import com.elanyudho.pokemon.R
import com.elanyudho.pokemon.databinding.ActivityDetailPokemonBinding
import com.elanyudho.pokemon.ui.detail.adapter.AbilityAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailPokemonActivity : BaseActivityBinding<ActivityDetailPokemonBinding>(), Observer<DetailPokemonViewModel.DetailPokemonUiState> {

    @Inject
    lateinit var detailPokemonViewModel: DetailPokemonViewModel

    @Inject
    lateinit var pokemonHelper: PokemonHelper

    private val abilityAdapter: AbilityAdapter by lazy { AbilityAdapter() }

    private lateinit var pokemonId: String

    override val bindingInflater: (LayoutInflater) -> ActivityDetailPokemonBinding
        get() = {ActivityDetailPokemonBinding.inflate(layoutInflater)}

    override fun setupView() {
        getDataIntent()
        initViewModel()
        setAdapter()
        setHeader()
    }

    override fun onChanged(state: DetailPokemonViewModel.DetailPokemonUiState?) {
        when (state) {
            is DetailPokemonViewModel.DetailPokemonUiState.Loading -> {
                startLoading()
            }

            is DetailPokemonViewModel.DetailPokemonUiState.DetailPokemonLoaded -> {
                stopLoading()
                showDetail(state.data)
            }

            is DetailPokemonViewModel.DetailPokemonUiState.FailedLoaded -> {
                stopLoading()
                handleFailure(state.failure)
            }

            else -> {}
        }
    }

    private fun getDataIntent() {
       pokemonId =  intent.getStringExtra("pokemonId").toString()
    }

    private fun initViewModel() {
        detailPokemonViewModel.uiState.observe(this, this)
        detailPokemonViewModel.getDetailPokemon(pokemonId)
    }

    private fun showDetail(data: DetailPokemon) {
        with(binding) {
            tvName.text = data.title.replaceFirstChar { it.uppercase() }
            imgPokemon.glide(this@DetailPokemonActivity, data.imgUrl)

            abilityAdapter.submitList(data.abilities)
        }
    }

    private fun setAdapter() {
        binding.rvAbility.apply {
            adapter = abilityAdapter
            setHasFixedSize(true)
        }
    }

    private fun startLoading() {
        with(binding) {
            binding.progressCircular.visibility = View.VISIBLE
            grpContent.visibility = View.GONE
        }
    }

    private fun stopLoading() {
        with(binding) {
            progressCircular.visibility = View.GONE
            grpContent.visibility = View.VISIBLE
        }

    }

    private fun handleFailure(failure: Failure) {
        Toast.makeText(this, failure.throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setHeader() {
        with(binding) {
            headerDetail.tvHeader.text = getString(R.string.about)
            headerDetail.btnBack.setOnClickListener { onBackPressed() }
        }
    }

    override fun onStop() {
        super.onStop()
        pokemonHelper.closeDb()
    }

}