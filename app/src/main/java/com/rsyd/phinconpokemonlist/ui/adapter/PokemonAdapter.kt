package com.rsyd.phinconpokemonlist.ui.adapter

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rsyd.phinconpokemonlist.R
import com.rsyd.phinconpokemonlist.databinding.ItemPokemonListBinding
import com.rsyd.phinconpokemonlist.network.model.PokemonModel
import com.rsyd.phinconpokemonlist.utils.getPictUrl

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.PokemonViwHolder>() {

    private var onItemClickListener: ((PokemonModel?) -> Unit)? = null
    private var pokemon = mutableListOf<PokemonModel>()

    fun setPokemon(pokemon: List<PokemonModel>) {
        val positionStart = this.pokemon.size
        this.pokemon.addAll(pokemon)
        notifyItemRangeInserted(positionStart, this.pokemon.size)
    }

    fun setOnItemClickListener(listener: (PokemonModel?) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViwHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViwHolder(ItemPokemonListBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViwHolder, position: Int) {
        val pokemon = this.pokemon.getOrNull(position)
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemon.size

    inner class PokemonViwHolder(private val binding: ItemPokemonListBinding): RecyclerView.ViewHolder(binding.root) {


        private var dominantColor: Int = Color.GRAY
        var picture: String? = ""

        init {
            binding.root.setOnClickListener{
                onItemClickListener?.invoke(pokemon.getOrNull(adapterPosition))
            }
        }
        fun bind(pokemon: PokemonModel?) {
            with(binding) {
                setPokemonImage(pokemon)
                tvPokemonName.text = pokemon?.name
            }
        }

        private fun setPokemonImage(pokemon: PokemonModel?) {
            Glide.with(itemView.context.applicationContext)
                .asBitmap()
                .load(pokemon?.url?.getPictUrl())
                .listener(object : RequestListener<Bitmap>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (resource != null) {
                            setBgColor(resource)
                        }
                        return false
                    }
                })
                .centerCrop()
                .into(binding.ivPokemon)
        }

        private fun setBgColor(resource: Bitmap) {
            Palette.Builder(resource).generate {
                it?.let { palette ->
                    when(binding.ivPokemon.context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                        Configuration.UI_MODE_NIGHT_YES -> {
                            dominantColor = palette.getMutedColor(Color.GRAY)
                        }
                        Configuration.UI_MODE_NIGHT_NO -> {
                            dominantColor = palette.getLightMutedColor(Color.GRAY)
                        }
                    }
                    binding.cl.setBackgroundColor(dominantColor)
                }
            }
        }
    }
}