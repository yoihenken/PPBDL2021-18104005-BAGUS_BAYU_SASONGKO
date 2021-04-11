package id.bagus.pokemonmvvm.ui.pokemoncarddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import coil.load
import id.bagus.pokemonmvvm.R
import id.bagus.pokemonmvvm.model.PokemonCard
import id.bagus.pokemonmvvm.model.PokemonCardDetailViewState
import kotlinx.android.synthetic.main.fragment_pokemon_card_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonCardDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonCardDetailFragment : Fragment() {
    private lateinit var vm: PokemonCardDetailViewModel
    private val args: PokemonCardDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_card_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonCard = args.PokemonCard
        vm = ViewModelProviders.of(this).get(PokemonCardDetailViewModel::class.java).apply {
            viewState.observe(
                this@PokemonCardDetailFragment,
                Observer(this@PokemonCardDetailFragment::handleState)
            )
            pokemonCard?.let { setData(it) }
        }
    }

    private fun handleState(viewState: PokemonCardDetailViewState) {
        viewState.data?.let { showDetail(it) }
    }

    private fun showDetail(data: PokemonCard) {
        ivLogo.load(data.image)
        tvName.text = data.name
        tvRarity.text = data.rarity
        tvSeries.text = data.series
    }

    companion object {
    }
}