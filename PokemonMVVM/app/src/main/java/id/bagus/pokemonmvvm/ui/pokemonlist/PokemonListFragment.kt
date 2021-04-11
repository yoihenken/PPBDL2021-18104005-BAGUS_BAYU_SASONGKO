package id.bagus.pokemonmvvm.ui.pokemonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import id.bagus.pokemonmvvm.R
import id.bagus.pokemonmvvm.model.PokemonCard
import id.bagus.pokemonmvvm.model.PokemonListViewState
import id.bagus.pokemonmvvm.repository.network.PokemonCardRepository
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonListFragment : Fragment() {
    private lateinit var vm: PokemonListViewModel
    private lateinit var adapter: PokemonListAdapter
    private val args: PokemonListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PokemonListAdapter()
        rvCard.adapter = adapter

        val factory = PokemonListViewModelFactory(PokemonCardRepository.instance)
        vm = ViewModelProviders.of(this, factory).get(PokemonListViewModel::class.java).apply {
            viewState.observe(
                this@PokemonListFragment,
                Observer(this@PokemonListFragment::handleState)
            )
            if (viewState.value?.data == null) getPokemons(args.setName.toString())
            srlCard.setOnRefreshListener { getPokemons(args.setName.toString()) }
        }
    }

    private fun handleState(viewState: PokemonListViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PokemonCard>) {
        adapter.updateData(data)
        tvCardError.visibility = View.GONE
        rvCard.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvCardError.text = error.message
        tvCardError.visibility = View.VISIBLE
        rvCard.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlCard.isRefreshing = loading
    }

    companion object {
    }
}