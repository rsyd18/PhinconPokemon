package com.rsyd.phinconpokemonlist.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("base_experience")
    val baseXP: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("types")
    val types: List<TypeModel>,
    @SerializedName("moves")
    val moves: List<MoveModel>

): Parcelable
