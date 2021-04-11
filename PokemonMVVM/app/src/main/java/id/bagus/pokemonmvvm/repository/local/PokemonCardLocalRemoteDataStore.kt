package id.bagus.pokemonmvvm.repository.local

import id.bagus.pokemonmvvm.model.PokemonCard
import id.bagus.pokemonmvvm.repository.network.PokemonTcgService


class PokemonCardLocalDataStore : PokemonCardDataStore {
    private val caches = mutableMapOf<String, MutableList<PokemonCard>?>()

    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? =
        if (caches.contains(set)) caches[set] else null

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
        caches[set] = pokemons
    }
}

class PokemonCardRemoteDataStore(private val pokemonTcgService: PokemonTcgService) : PokemonCardDataStore {
    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonTcgService.getCards(set)
        if (response.isSuccessful) return response.body()?.cards

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
    }
}