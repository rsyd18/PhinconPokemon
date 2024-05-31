package com.rsyd.phinconpokemonlist.network

import com.rsyd.phinconpokemonlist.network.model.PokemonDetailModel
import com.rsyd.phinconpokemonlist.network.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ) : PokemonResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetail(
        @Path("id") id: Int?
    ): PokemonDetailModel
}