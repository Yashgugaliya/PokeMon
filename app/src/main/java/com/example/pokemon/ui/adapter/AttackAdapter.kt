package com.example.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ListAttacksBinding
import com.example.pokemon.model.Attacks

class AttackAdapter(private val list: ArrayList<Attacks>) : RecyclerView.Adapter<AttackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttackViewHolder =
        AttackViewHolder(ListAttacksBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AttackViewHolder, position: Int) {
        holder.bindTags(list[position]) }

    override fun getItemCount(): Int = list.size

}

class AttackViewHolder(private val binding: ListAttacksBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTags(attacks: Attacks) {
        binding.apply {
            tvName.text = attacks.name
            tvSub.text = attacks.text
            tvDamage.text = "Damage : ${attacks.damage}"
            tvConvert.text = "Converted Energy Cost : ${attacks.convertedEnergyCost}"
        }

    }

}