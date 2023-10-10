package com.elanyudho.pokemon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.core.abstraction.PagingRecyclerViewAdapter
import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.pokemon.databinding.ItemPokemonBinding

class PokemonAdapter : PagingRecyclerViewAdapter<PokemonAdapter.PokemonViewHolder, Pokemon>() {

    private var onClick: ((Pokemon) -> Unit)? = null

    override val holderInflater: (LayoutInflater, ViewGroup, Boolean) -> PokemonViewHolder
        get() = { inflater, viewGroup, boolean ->
            PokemonViewHolder(ItemPokemonBinding.inflate(inflater, viewGroup, boolean))
        }

    inner class PokemonViewHolder(itemView: ItemPokemonBinding) :
        BaseViewHolder<Pokemon, ItemPokemonBinding>(itemView) {
        override fun bind(data: Pokemon) {
            with(binding) {
                tvName.text = data.name

                root.setOnClickListener {
                    onClick?.invoke(data)

                }
            }

        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    fun appendList(newData: List<Pokemon>) {
        newData.forEach {
            if (!listData.any { existingPokemon -> existingPokemon.name == it.name }) {
                listData.add(it)
            }
            notifyDataSetChanged()
        }
    }

    fun setOnClickData(onClick: (data: Pokemon) -> Unit) {
        this.onClick = onClick
    }
}

