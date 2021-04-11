package id.bagus.pokemonmvvm.ui.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.bagus.pokemonmvvm.R
import id.bagus.pokemonmvvm.model.PokemonCard
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    private val pokemonCards = mutableListOf<PokemonCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
    )

    override fun getItemCount() = pokemonCards.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonCards[position])
    }

    fun updateData(newPokemonCards: MutableList<PokemonCard>) {
        pokemonCards.clear()
        pokemonCards.addAll(newPokemonCards)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(pokemonCard: PokemonCard) {

            itemView.apply {
                ivCardLogo.load(pokemonCard.image)
                tvCardName.text = pokemonCard.name
                tvCardRarity.text = pokemonCard.rarity
            }

            containerView?.setOnClickListener { view ->
                val action = PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonCardDetailFragment(
                        pokemonCard,
                        pokemonCard.name!!
                    )
                view.findNavController().navigate(action)
            }
        }
    }
}