package com.rsyd.phinconpokemonlist.utils


fun String.extractId() = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.getPictUrl(): String{
    val id = this.extractId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}