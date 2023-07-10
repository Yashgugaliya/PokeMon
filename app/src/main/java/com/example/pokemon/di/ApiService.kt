package com.example.pokemon.di

import com.example.pokemon.model.PokeResp
import com.example.pokemon.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.URL_REPOSITORIES)
    suspend fun getData(): PokeResp

    @GET(Constants.URL_REPOSITORIES)
    suspend fun getSearchData(@Query("q") search : String): PokeResp
}