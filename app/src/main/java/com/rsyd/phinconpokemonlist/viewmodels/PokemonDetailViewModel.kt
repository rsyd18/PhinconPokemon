package com.rsyd.phinconpokemonlist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsyd.phinconpokemonlist.network.model.PokemonDetailModel
import com.rsyd.phinconpokemonlist.repository.PokemonRepository
import com.rsyd.phinconpokemonlist.utils.extractId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetailModel?>()
    val pokemonDetail : LiveData<PokemonDetailModel?> = _pokemonDetail

    var name: String? = null
    var url: String? = null

    fun getPokemonDetail() {
        val id = url?.extractId()
        viewModelScope.launch {
            _pokemonDetail.value = pokemonRepository.getPokemonDetail(id)
        }
    }

}