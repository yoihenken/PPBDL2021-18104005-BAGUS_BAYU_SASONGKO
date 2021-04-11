package id.bagus.pokemonmvvm.model

data class PokemonListViewState (
    val loading: Boolean = false,
    val error: Exception? = null,
    val data: MutableList<PokemonCard>? = null
)