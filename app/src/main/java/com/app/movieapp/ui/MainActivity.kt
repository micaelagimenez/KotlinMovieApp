package com.app.movieapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.app.movieapp.R
import com.app.movieapp.adapter.RecyclerAdapter
import com.app.movieapp.databinding.ActivityMainBinding
import com.app.movieapp.model.Movie
import com.app.movieapp.utils.NetworkStatus


class MainActivity : AppCompatActivity(), RecyclerAdapter.Callback, androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private val movies = mutableListOf<Movie>()
    private lateinit var adapter: RecyclerAdapter
    private lateinit var progressBar: ProgressBar

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { MainViewModelFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svSearch.setOnQueryTextListener(this)
        //setup progress bar
        progressBar = binding.ProgressBar
        progressBar.visibility = View.VISIBLE

        setObservers()
        initRecyclerView()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id== R.id.action_refresh){
            movies.clear()
            setObservers()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        adapter = RecyclerAdapter(movies)
        binding.rvMovies.layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.adapter = adapter
        adapter.callback = this
    }

    private fun setObservers() {
        viewModel.movieList.observe(this, Observer {
            when (it.status) {
                NetworkStatus.LOADING -> {
                    //show loading state
                    progressBar.visibility = View.VISIBLE
                }
                NetworkStatus.SUCCESS -> {
                    //hide loading state
                    progressBar.visibility = View.INVISIBLE
                    //render character list
                    for (movie in it.data!!) {
                        movies.add(movie)
                    }
                    adapter.notifyDataSetChanged()
                }
                NetworkStatus.ERROR -> {
                    //show error message
                    Toast.makeText(this, "Error loading content", Toast.LENGTH_SHORT).show()
                    //hide loading state
                    progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }



    //add search functionality by title
    private fun searchByTitle(query: String) {
        val movie = movies.filter { it.title.contains(query, true) }

        if (movie.isNotEmpty()) {
            movies.clear()
            movies.addAll(movie)
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(applicationContext, "Title not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByTitle(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    //send details of clicked movie to fragment
    override fun onMovieClicked(movie: Movie) {
        val transaction = supportFragmentManager
        val detailFragment = DetailFragment()
        val data = Bundle()

        //transfer data to fragment
        data.putString("Title", movie.title)
        data.putString("Image", movie.poster)
        data.putString("Overview", movie.overview)
        data.putString("Language", movie.originalLanguage)
        data.putString("Release Date", movie.releaseDate)
        data.putFloat("Popularity", movie.popularity)
        data.putFloat("Vote average", movie.voteAverage)
        detailFragment.arguments = data

        transaction.beginTransaction()
            .replace(R.id.frag_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}