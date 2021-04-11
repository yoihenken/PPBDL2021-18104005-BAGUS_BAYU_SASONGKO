package id.bagus.pokemonmvvm.ui.pokemoncarddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.bagus.pokemonmvvm.model.PokemonCard
import id.bagus.pokemonmvvm.model.PokemonCardDetailViewState

class PokemonCardDetailViewModel : ViewModel() {
    private val mViewState = MutableLiveData<PokemonCardDetailViewState>().apply {
        value = PokemonCardDetailViewState(null)
    }
    val viewState: LiveData<PokemonCardDetailViewState>
        get() = mViewState

    fun setData(pokemonCard: PokemonCard) {
        mViewState.value = mViewState.value?.copy(data = pokemonCard)
    }
}