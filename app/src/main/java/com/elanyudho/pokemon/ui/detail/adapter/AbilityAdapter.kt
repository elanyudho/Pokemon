package com.elanyudho.pokemon.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elanyudho.core.abstraction.BaseViewHolder
import com.elanyudho.pokemon.databinding.ItemAbilityBinding

class AbilityAdapter: RecyclerView.Adapter<AbilityAdapter.Holder>() {

    private var listData = ArrayList<String>()

    fun submitList(newList: List<String>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbilityAdapter.Holder {
        return Holder(ItemAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: AbilityAdapter.Holder,
        position: Int
    ) {
        holder.bind(data = listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder (itemView: ItemAbilityBinding) :
        BaseViewHolder<String, ItemAbilityBinding>(itemView){
        override fun bind(data: String) {
            with(binding) {
                tvName.text = data

            }
        }
    }

}