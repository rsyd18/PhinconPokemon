package com.rsyd.phinconpokemonlist.datasources

import com.rsyd.phinconpokemonlist.network.ApiService
import com.rsyd.phinconpokemonlist.network.model.PokemonDetailModel
import com.rsyd.phinconpokemonlist.network.model.PokemonResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PokemonDataSource @Inject constructor(
    private val apiService: ApiService
): CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getPokemons(limit: Int, offset: Int): PokemonResponse? {
        return withContext(coroutineContext) {
            try {
                apiService.getPokemons(limit, offset)
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getPokemonDetail(id: Int?): PokemonDetailModel? {
        return withContext(coroutineContext) {
            try {
                apiService.getPokemonDetail(id)
            } catch (e: Exception) {
                null
            }
        }
    }
}