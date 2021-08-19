package com.app.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.R
import com.app.movieapp.databinding.ItemFavBinding
import com.app.movieapp.db.Favorites
import com.app.movieapp.utils.Constants
import com.squareup.picasso.Picasso

class FavoritesAdapter(val deleteHandler: (Favorites) -> Unit ): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var favoriteList = emptyList<Favorites>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavBinding.bind(itemView)

        val favTitle: TextView = binding.tvFavTitle
        val favItem: ImageButton = binding.btnFavItem

        fun bind(favorites: Favorites) {
            Picasso.get().load(Constants.BASE_IMAGE_URL + favorites.poster).into(binding.ivFavsImage)
            favTitle.text = favorites.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteList[position])

        //delete favorite item
        holder.favItem.setOnClickListener {
            deleteHandler(favoriteList[position])
        }

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun setData(favorite: List<Favorites>){
        this.favoriteList = favorite
        notifyDataSetChanged()
    }

}