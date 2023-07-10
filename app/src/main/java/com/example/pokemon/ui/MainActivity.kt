package com.example.pokemon.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.R
import com.example.pokemon.ui.adapter.PokeAdapter
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.model.Data
import com.example.pokemon.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var levelList = listOf<Data>()
    private var hpList = listOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
        setUpObserver()
    }

    private fun setUpObserver() {
        mainViewModel.response.observe(this) {
            hpList = it.data.sortedBy { data -> data.hp.toInt() }
            levelList = it.data.sortedBy { data2 -> data2.level }
            if (it.data.size != 0 && it.data.isNotEmpty()) {
                setUpAdapter(it.data)
            } else {
                binding.rv.visibility = View.GONE
                binding.pb.visibility = View.GONE
                binding.tvError.visibility = View.VISIBLE
            }
        }
        mainViewModel.errorLiveData.observe(this) {
            binding.pb.visibility = View.GONE
            Toast.makeText(this, "Something Went Wrong ", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setUpAdapter(list: List<Data>){
        binding.rv.adapter = PokeAdapter(list, this@MainActivity)
        binding.pb.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.rv.visibility = View.VISIBLE
    }

    private fun setUpMenu(){

        binding.ivFilter.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, it)
            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when(menuItem.title){
                    "Level" -> { setUpAdapter(levelList) }
                    "HP" -> { setUpAdapter(hpList) }
                }
                true
            }
            // Showing the popup menu
            popupMenu.show()
        }
    }

    private fun setUpSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mainViewModel.getSearchData("name:$query")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        val closeButton: View? =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            binding.rv.visibility = View.GONE
            binding.pb.visibility = View.VISIBLE
            mainViewModel.getData()
            binding.searchView.setQuery("", false)
        }
    }

    private fun setUi() {
        makeApiCall()
        setUpSearch()
        setUpMenu()
        binding.rv.layoutManager = GridLayoutManager(this@MainActivity, 2)
    }

    private fun makeApiCall() {
        mainViewModel.getData()
    }

}