package id.bagus.pokemonmvvm.repository.local

import id.bagus.pokemonmvvm.model.PokemonSet

interface PokemonSetDataStore {
    suspend fun getSets(): MutableList<PokemonSet>?
    suspend fun addAll(sets: MutableList<PokemonSet>?)
}