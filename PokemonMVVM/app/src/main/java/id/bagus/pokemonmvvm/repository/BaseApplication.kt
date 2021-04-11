package id.bagus.pokemonmvvm.repository

import android.app.Application
import id.bagus.pokemonmvvm.repository.local.*
import id.bagus.pokemonmvvm.repository.network.PokemonCardRepository
import id.bagus.pokemonmvvm.repository.network.PokemonSetRepository
import id.bagus.pokemonmvvm.repository.network.RetrofitApp

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonTcgService = RetrofitApp.POKEMON_TCG_SERVICE
        val appDatabase = AppDatabase.getInstance(this)
        PokemonSetRepository.instance.apply {
            init(
                PokemonSetRoomDataStore(appDatabase.pokemonSetDao()),
                PokemonSetRemoteDataStore(pokemonTcgService)
            )
        }

        PokemonCardRepository.instance.apply {
            init(
                PokemonCardRoomDataStore(appDatabase.pokemonCardDao()),
                PokemonCardRemoteDataStore(pokemonTcgService)
            )
        }
    }
}