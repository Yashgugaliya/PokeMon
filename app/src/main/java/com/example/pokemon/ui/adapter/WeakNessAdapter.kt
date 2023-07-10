package com.example.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ListWeakBinding
import com.example.pokemon.model.Weaknesses

class WeakNessAdapter(private val list: ArrayList<Weaknesses>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListWeakBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTags(list[position])
    }
}

class ViewHolder(private val binding: ListWeakBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTags(data: Weaknesses) {
        binding.apply {
            tvName.text = "Type : ${data.type}"
            tvValue.text = "Value : ${data.value}"
        }

    }

}