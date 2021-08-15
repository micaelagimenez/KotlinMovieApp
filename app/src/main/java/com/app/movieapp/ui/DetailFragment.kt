package com.app.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.movieapp.R
import com.app.movieapp.databinding.FragmentDetailBinding
import com.app.movieapp.utils.Constants
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: RatingViewModel by viewModels(
        factoryProducer = { RatingViewModelFactory() }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        //setting up variables for the view binding
        val itemTextTitle: TextView = binding.tvTitleDetail
        val itemTextOverview: TextView = binding.tvOverview
        val itemTextLanguage: TextView = binding.tvLanguage
        val itemTextReleaseDate: TextView = binding.tvReleaseDate
        val itemTextPopularity: TextView = binding.tvPopularity
        val itemTextVoteAverage: TextView = binding.tvVoteAverage
        val itemImage: ImageView = binding.ivPosterDetail
        //get data from activity
        if (arguments != null) {
            val titleString = arguments?.getString("Title")
            val imageString = arguments?.getString("Image")
            val overviewString = arguments?.getString("Overview")
            val languageString = arguments?.getString("Language")
            val releaseDateString = arguments?.getString("Release Date")
            val popularityString = arguments?.getFloat("Popularity")
            val voteAverageString = arguments?.getFloat("Vote average")
            //bind data to view
            itemTextTitle.text = titleString
            itemTextOverview.text = getString(R.string.overview_detail, overviewString)
            itemTextLanguage.text = getString(R.string.language_detail, languageString)
            itemTextReleaseDate.text = releaseDateString
            itemTextPopularity.text = getString(R.string.popularity_detail, popularityString.toString().format("%.3f"))
            itemTextVoteAverage.text = getString(R.string.vote_average_detail, voteAverageString.toString().format("%.1f"))
            Picasso.get().load(Constants.BASE_IMAGE_URL + imageString).into(itemImage)
        } else {
            //display error message if arguments are null
            Toast.makeText(context, "Error loading content", Toast.LENGTH_SHORT).show()
        }
        setupRatingBarWithChanges()
        ratingButton()

        return binding.root
    }
    //hide menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    //call the observers for post request
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    //setup rating bar
    private fun setupRatingBarWithChanges() {
        val ratingBarChanges = binding.ratingBar
        setRatingText(numStars = ratingBarChanges.numStars)

        ratingBarChanges.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            setRatingText(rating, ratingBar.numStars)
        }
    }

    private fun setRatingText(rating: Float = 0f, numStars: Int) {
        binding.txtRatingValue.text = formatRating(rating, numStars)
    }

    private fun formatRating(rating: Float, numStars: Int) = "$rating/${numStars}"

    //setup observers to let user know if post request was successful or not
    private fun setObservers(){
        viewModel.success.observe(viewLifecycleOwner, { success->
            if (success) {
                Toast.makeText(context, "Request succeeded", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Request failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //rating movie button
    private fun ratingButton() {
        binding.btnRating.setOnClickListener {
            arguments?.getInt("Id")?.let {
                arguments?.getString("Session Id").let { it1 ->
                    if (it1 != null) {
                        //send post request through view model
                        viewModel.postRating(it, mapOf("value" to binding.ratingBar.rating), it1)
                    }
                }
            }
        }
    }
}