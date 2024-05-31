package com.rsyd.phinconpokemonlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsyd.phinconpokemonlist.databinding.ItemMoveBinding
import com.rsyd.phinconpokemonlist.network.model.MoveModel

class PokemonDetailAdapter: RecyclerView.Adapter<PokemonDetailAdapter.DetailViewHolder>() {

    private var pokemonMove = mutableListOf<MoveModel>()

    fun setPokemonMove(pokemonMove: List<MoveModel>) {
        val  positionStart = this.pokemonMove.size
        this.pokemonMove.addAll(pokemonMove)
        notifyItemRangeInserted(positionStart, this.pokemonMove.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DetailViewHolder(ItemMoveBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val pokemonMove = this.pokemonMove.getOrNull(position)
        holder.bind(pokemonMove)
    }

    override fun getItemCount(): Int = this.pokemonMove.size

    inner class DetailViewHolder(private val binding: ItemMoveBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(move: MoveModel?) {
            with(binding) {
                tvPokemonMove.text = move?.move?.moveName
            }
        }
    }
}