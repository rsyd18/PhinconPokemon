package com.rsyd.phinconpokemonlist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsyd.phinconpokemonlist.databinding.ActivityMainBinding
import com.rsyd.phinconpokemonlist.ui.adapter.PokemonAdapter
import com.rsyd.phinconpokemonlist.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pokemonAdapter by lazy { PokemonAdapter() }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPokemons()
        setupObserver()
        setupRecyclerView()
    }
    private fun getPokemons() {
        viewModel.getPokemon()
    }

    private fun setupObserver() {
        with(viewModel) {
            pokemon.observe(this@MainActivity) { pokemon ->
                pokemonAdapter.setPokemon(pokemon.orEmpty())
                Log.d("MainPokemon", pokemon.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        with(binding.rvPokemon) {
            this.layoutManager = layoutManager
            adapter = pokemonAdapter.apply {
                setOnItemClickListener {
                    startActivity(PokemonDetailActivity.newIntent(this@MainActivity, it?.name, it?.url))
                }
            }
        }
    }
}