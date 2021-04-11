package id.bagus.pokemonmvvm.repository.local

import id.bagus.pokemonmvvm.model.PokemonSet

class PokemonSetRoomDataStore(private val pokemonSetDao: PokemonSetDao) : PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonSetDao.getAll()
        return if (response.isEmpty()) null else response
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        sets?.let { pokemonSetDao.insertAll() }
    }
}