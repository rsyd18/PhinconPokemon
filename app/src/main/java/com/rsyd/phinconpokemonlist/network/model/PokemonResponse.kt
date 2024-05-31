package com.rsyd.phinconpokemonlist.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResponse(
    @SerializedName("count")
    val count : Int,
    @SerializedName("next")
    val next : String?,
    @SerializedName("previous")
    val previous : String?,
    @SerializedName("results")
    val results : List<PokemonModel>
) : Parcelable
