package id.bagus.pokemonmvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.bagus.pokemonmvvm.model.MainViewState
import id.bagus.pokemonmvvm.repository.network.PokemonSetRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val pokemonSets: PokemonSetRepository
) : ViewModel() {
    private val mViewState = MutableLiveData<MainViewState>().apply {
        value = MainViewState(loading = true)
    }
    val viewState: LiveData<MainViewState>
        get() = mViewState

    init {
        getSets()
    }

    fun getSets() = viewModelScope.launch {
        try {
            val data = pokemonSets.getSets()
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}