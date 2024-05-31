package com.rsyd.phinconpokemonlist.repository

import com.rsyd.phinconpokemonlist.datasources.PokemonDataSource
import com.rsyd.phinconpokemonlist.network.model.PokemonDetailModel
import com.rsyd.phinconpokemonlist.network.model.PokemonResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonDataSource: PokemonDataSource) {

    suspend fun getPokemons(): PokemonResponse? {
        return pokemonDataSource.getPokemons(100, 100)
    }

    suspend fun getPokemonDetail(id: Int?): PokemonDetailModel? {
        return pokemonDataSource.getPokemonDetail(id)
    }

}