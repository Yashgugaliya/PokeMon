package com.example.pokemon.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.ui.DetailFragment
import com.example.pokemon.ui.MainActivity
import com.example.pokemon.R
import com.example.pokemon.databinding.ListItemBinding
import com.example.pokemon.model.Data

class PokeAdapter(private val listOfTags: List<Data>, private val context: Context) : RecyclerView.Adapter<PokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder =
        PokeViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.bindTags(listOfTags[position], context) }

    override fun getItemCount(): Int = listOfTags.size

}

class PokeViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTags(data: Data, c: Context) {
        binding.apply {
            Glide.with(c).load(data.images.small).placeholder(R.drawable.ic_launcher_foreground).into(iv)
            tvName.text = data.name
            data.level.let { tvLevel.text = "Level : $it"  }
            tvHP.text = "${data.hp} HP"
            data.types[0].let {  tvType.text = "Type : $it"  }
        }

        binding.root.setOnClickListener {
            val activity = itemView.context as? MainActivity
            activity?.supportFragmentManager?.let { it1 ->
                DetailFragment.newInstance(data)
                    .show(it1, "DailyStreakDialogFragment")
            }
        }
    }

}