package com.example.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.model.PokeResp
import com.example.pokemon.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MainRepository) : ViewModel(){

    private val _response: MutableLiveData<PokeResp> = MutableLiveData()
    val response: LiveData<PokeResp> = _response

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData = _errorLiveData

    fun getData(){
        viewModelScope.launch {
            repo.getData()
                .catch {e->
                    Log.e("MainViewModel_ERROR", "getPost: ${e.message}")
                    _errorLiveData.value = e.message
                }.collect {response->
                    _response.value = response
                }

        }
    }
    fun getSearchData(search : String){
        viewModelScope.launch {
            repo.getSearchData(search)
                .catch {e->
                    Log.e("MainViewModel_ERROR", "getPost: ${e.message}")
                    _errorLiveData.value = e.message
                }.collect {response->
                    _response.value = response
                }

        }
    }

}