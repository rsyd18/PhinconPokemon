package com.rsyd.phinconpokemonlist.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rsyd.phinconpokemonlist.R
import com.rsyd.phinconpokemonlist.databinding.ActivityPokemonDetailBinding
import com.rsyd.phinconpokemonlist.network.model.PokemonDetailModel
import com.rsyd.phinconpokemonlist.network.model.PokemonModel
import com.rsyd.phinconpokemonlist.network.model.TypeModel
import com.rsyd.phinconpokemonlist.ui.adapter.PokemonDetailAdapter
import com.rsyd.phinconpokemonlist.utils.extractId
import com.rsyd.phinconpokemonlist.utils.getPictUrl
import com.rsyd.phinconpokemonlist.viewmodels.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_POKEMON_NAME = "EXTRA_POKEMON_NAME"
        const val EXTRA_URL = "EXTRA_URL"

        fun newIntent(context: Context, name: String?, url: String?) = Intent(context, PokemonDetailActivity::class.java).apply {
            putExtra(EXTRA_POKEMON_NAME, name)
            putExtra(EXTRA_URL, url)
        }
    }

    private lateinit var binding: ActivityPokemonDetailBinding
    private val pokemonDetailAdapter by lazy { PokemonDetailAdapter() }
    private val viewModel by viewModels<PokemonDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.name = intent.getStringExtra(EXTRA_POKEMON_NAME)
        viewModel.url = intent.getStringExtra(EXTRA_URL)

        setupObserver()
        setupRecyclerView()

        viewModel.getPokemonDetail()
    }

    private fun setupObserver() {
        with(viewModel) {
            pokemonDetail.observe(this@PokemonDetailActivity) {
                pokemonDetailAdapter.setPokemonMove(it?.moves.orEmpty())
                if (it != null) {
                    setupView(it)
                }
                Log.d("DetailMove", it.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvMove) {
            adapter = pokemonDetailAdapter
            layoutManager = LinearLayoutManager(this@PokemonDetailActivity)
        }
    }

    private fun setupView(model: PokemonDetailModel) {
        with(binding) {
            Glide.with(root)
                .load(viewModel.url?.getPictUrl())
                .placeholder(R.drawable.ic_default)
                .centerCrop()
                .into(ivPokemon)
            tvPokemonId.text = viewModel.url?.extractId().toString()
            tvPokemonTypeOne.text = model.types[0].type.TypeName
            setPokemonTypes(model.types)
            tvPokemonName.text = model?.name
            tvPokemonXp.text = model?.baseXP.toString()
            tvPokemonWeight.text = model?.weight.toString()
            tvPokemonHeight.text = model?.height.toString()

        }
    }

    private fun setPokemonTypes(types : List<TypeModel>){
        with(binding){
            if(types.size > 1){
                tvPokemonTypeTwo.text = types[1].type.TypeName
                tvPokemonTypeTwo.visibility = View.VISIBLE
            } else{
                tvPokemonTypeTwo.visibility = View.GONE
            }
        }
    }

}