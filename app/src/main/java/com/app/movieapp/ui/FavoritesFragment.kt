package com.app.movieapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapp.adapter.FavoritesAdapter
import com.app.movieapp.databinding.FragmentFavoritesBinding
import com.app.movieapp.db.FavoriteViewModel
import com.app.movieapp.db.Favorites


class FavoritesFragment : Fragment() {

    private lateinit var mfavoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoritesBinding
    private val deleteHandler: (Favorites)-> Unit = {
        mfavoriteViewModel.deleteFavorite(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        //recyclerview
        val adapter = FavoritesAdapter(deleteHandler)
        binding.rvFavList.layoutManager = LinearLayoutManager(context)
        binding.rvFavList.adapter = adapter

        //favoriteViewModel
        mfavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        mfavoriteViewModel.readAllData.observe(viewLifecycleOwner, Observer { favorite ->
            adapter.setData(favorite)
        })

        return binding.root
    }

}