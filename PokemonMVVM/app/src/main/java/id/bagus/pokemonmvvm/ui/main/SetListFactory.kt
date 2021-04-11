package id.bagus.pokemonmvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.bagus.pokemonmvvm.repository.network.PokemonSetRepository

class SetListFactory(
    private val setRepository: PokemonSetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(setRepository) as T
        throw IllegalArgumentException()
    }
}