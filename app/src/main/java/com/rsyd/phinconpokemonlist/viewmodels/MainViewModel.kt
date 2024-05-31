package com.rsyd.phinconpokemonlist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsyd.phinconpokemonlist.network.model.PokemonModel
import com.rsyd.phinconpokemonlist.network.model.PokemonResponse
import com.rsyd.phinconpokemonlist.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private var pokemonResponse: PokemonResponse? = null

    private val _pokemon = MutableLiveData<List<PokemonModel>?>()
    val pokemon : LiveData<List<PokemonModel>?> = _pokemon

    fun getPokemon() {

        viewModelScope.launch {
            pokemonResponse = pokemonRepository.getPokemons()

            _pokemon.value = pokemonResponse?.results

        }

    }

}