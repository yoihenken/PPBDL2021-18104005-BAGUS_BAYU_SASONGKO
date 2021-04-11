package id.bagus.pokemonmvvm.model

data class MainViewState (
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonSet>? = null
)