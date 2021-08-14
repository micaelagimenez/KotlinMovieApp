package com.app.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.R
import com.app.movieapp.databinding.ItemMovieBinding
import com.app.movieapp.model.Movie
import com.app.movieapp.utils.Constants.Companion.BASE_IMAGE_URL
import com.squareup.picasso.Picasso

class RecyclerAdapter (private var Movies: List<Movie>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        private val binding = ItemMovieBinding.bind(view)

        val itemTitle: TextView = binding.tvTitle
        val itemImage: ImageView = binding.ivPoster

        fun bind(movies: Movie){
            Picasso.get().load(BASE_IMAGE_URL + movies.poster).into(itemImage)
            itemTitle.text = movies.title
        }
    }

    interface Callback {
        fun onMovieClicked(movieClicked: Movie)
    }

    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Movies[position])

        holder.itemImage.setOnClickListener {
            callback?.onMovieClicked(Movies[position])
        }
    }

    override fun getItemCount(): Int {
        return Movies.size
    }
}