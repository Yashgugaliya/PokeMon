package com.example.pokemon.repository

import com.example.pokemon.di.ApiService
import com.example.pokemon.model.PokeResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService ) {


    fun getData(): Flow<PokeResp> = flow {
        val response = apiService.getData()
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getSearchData(search : String): Flow<PokeResp> = flow {
        val response = apiService.getSearchData(search)
        emit(response)
    }.flowOn(Dispatchers.IO)
}