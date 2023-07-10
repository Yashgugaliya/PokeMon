package com.example.pokemon.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.ui.adapter.AbilitiesAdapter
import com.example.pokemon.ui.adapter.AttackAdapter
import com.example.pokemon.ui.adapter.WeakNessAdapter
import com.example.pokemon.databinding.FragmentDetailBinding
import com.example.pokemon.model.Data


class DetailFragment : DialogFragment() {

    private var item: Data? = null
    private lateinit var binding : FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { item = it.getParcelable(LIST) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        Glide.with(this).load(item?.images?.small).placeholder(R.drawable.ic_launcher_foreground).into(binding.ivImage)
        binding.tvName.text = item?.name
        binding.tvHP.text = "${item?.hp} HP"
        item?.level.let {  binding.tvLevel.text = "Level : $it"  }
        binding.type.adapter = ArrayAdapter(requireContext(), android.R.layout.test_list_item, arrayListOf( item?.types))
        binding.subType.adapter = ArrayAdapter(requireContext(), android.R.layout.test_list_item, arrayOf( item?.subtypes))

        binding.rvAttack.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = item?.attacks?.let { AttackAdapter(it) }
        }
        if (item?.weaknesses?.size !=0 && item?.weaknesses?.isNotEmpty() == true) {
            binding.clWeak.visibility = View.VISIBLE
            binding.rvWeak.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = item?.weaknesses?.let { WeakNessAdapter(it) }
            }
        } else {
            binding.clWeak.visibility = View.GONE
        }

        if (item?.resistances?.size != 0 && item?.resistances?.isNotEmpty() == true) {
            binding.clRes.visibility = View.VISIBLE
            binding.rvRes.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = item?.resistances?.let { WeakNessAdapter(it) }
            }
        } else {
            binding.clRes.visibility = View.GONE
        }

        if (item?.abilities?.size != 0 && item?.abilities?.isNotEmpty() == true) {
            binding.clAb.visibility = View.VISIBLE
            binding.rvAbility.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = item?.abilities?.let { AbilitiesAdapter(it) }
            }
        } else {
            binding.clAb.visibility = View.GONE
        }

        binding.ivBack.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    companion object {
        private const val LIST = "list"
        fun newInstance(data: Data) = DetailFragment().apply {
                arguments = Bundle().apply { putParcelable(LIST, data) }
            }
    }
    override fun getTheme(): Int = R.style.DialogTheme
}