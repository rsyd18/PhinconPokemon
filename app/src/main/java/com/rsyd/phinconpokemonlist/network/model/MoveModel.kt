package com.rsyd.phinconpokemonlist.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoveModel(
    @SerializedName("move")
    val move : Move
): Parcelable

@Parcelize
data class Move(
    @SerializedName("name")
    val moveName : String
): Parcelable
