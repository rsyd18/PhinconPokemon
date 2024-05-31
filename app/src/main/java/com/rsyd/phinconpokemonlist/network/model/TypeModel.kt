package com.rsyd.phinconpokemonlist.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypeModel(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: Type

) : Parcelable

@Parcelize
data class Type(
    @SerializedName("name")
    val TypeName: String
): Parcelable
