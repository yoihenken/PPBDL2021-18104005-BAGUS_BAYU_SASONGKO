package id.bagus.pokemonmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.bagus.pokemonmvvm.repository.network.PokemonCardRepository
import id.bagus.pokemonmvvm.ui.pokemonlist.PokemonListViewModel
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PokemonListViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var pokemonRepository: PokemonCardRepository? = null

    var pokemonListViewModel: PokemonListViewModel? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonListViewModel = PokemonListViewModel(pokemonRepository!!)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldLoadingWhenFirstInitialized() {
        val state = pokemonListViewModel!!.viewState.value!!
        assertTrue(state.loading)
        assertNull(state.error)
        assertNull(state.data)
    }

    @Test
    fun shouldStopLoadingAndGiveDataWhenSuccess() {
        runBlocking {
            `when`(pokemonRepository?.getPokemons(anyString())).thenReturn(mutableListOf())
            pokemonListViewModel?.getPokemons(anyString())
            val state = pokemonListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNull(state.error)
            assertNotNull(state.data)
        }
    }

    @Test
    fun shouldThrowErrorWhenRepositoryIsThrowingError() {
        runBlocking {
            `when`(pokemonRepository?.getPokemons(anyString())).thenAnswer { throw Exception() }
            pokemonListViewModel?.getPokemons(anyString())
            val state = pokemonListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertNull(state.data)
        }
    }
}