package id.bagus.pokemonmvvm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.bagus.pokemonmvvm.R
import id.bagus.pokemonmvvm.model.MainViewState
import id.bagus.pokemonmvvm.model.PokemonSet
import id.bagus.pokemonmvvm.repository.network.PokemonSetRepository
import kotlinx.android.synthetic.main.fragment_set_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetListFragment : Fragment() {
    private lateinit var vm: MainViewModel
    private lateinit var adapter: SetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SetListAdapter()
        rvSet.adapter = adapter

        val factory = SetListFactory(PokemonSetRepository.instance)
        vm = ViewModelProviders.of(this, factory).get(MainViewModel::class.java).apply {
            viewState.observe(this@SetListFragment, Observer(this@SetListFragment::handleState))
            srlSet.setOnRefreshListener { getSets() }
        }
    }

    private fun handleState(viewState: MainViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PokemonSet>) {
        adapter.updateData(data)
        tvSetError.visibility = View.GONE
        rvSet.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvSetError.text = error.message
        tvSetError.visibility = View.VISIBLE
        rvSet.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlSet.isRefreshing = loading
    }

    companion object {
    }
}