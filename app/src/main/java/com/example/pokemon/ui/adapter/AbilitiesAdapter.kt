package com.example.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ListAbilityBinding
import com.example.pokemon.model.Ability

class AbilitiesAdapter(private val list: ArrayList<Ability>) : RecyclerView.Adapter<AbilitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder =
        AbilitiesViewHolder(ListAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        holder.bindTags(list[position])
    }
}

class AbilitiesViewHolder(private val binding: ListAbilityBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTags(data: Ability) {
        binding.apply {
            tvName.text = data.name
            tvSub.text = data.text
            tvDamage.text = "Type : ${data.type}"
        }

    }

}