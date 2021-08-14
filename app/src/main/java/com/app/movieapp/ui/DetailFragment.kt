package com.app.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.movieapp.R
import com.app.movieapp.databinding.FragmentDetailBinding
import com.app.movieapp.utils.Constants
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        //binding the data received to the fragment
        val itemTextTitle: TextView = binding.tvTitleDetail
        val itemTextOverview: TextView = binding.tvOverview
        val itemTextLanguage: TextView = binding.tvLanguage
        val itemTextReleaseDate: TextView = binding.tvReleaseDate
        val itemTextPopularity: TextView = binding.tvPopularity
        val itemTextVoteAverage: TextView = binding.tvVoteAverage
        val itemImage: ImageView = binding.ivPosterDetail

        if(arguments != null){
            val titleString = arguments?.getString("Title")
            val imageString = arguments?.getString("Image")
            val overviewString = arguments?.getString("Overview")
            val languageString = arguments?.getString("Language")
            val releaseDateString = arguments?.getString("Release Date")
            val popularityString = arguments?.getFloat("Popularity")
            val voteAverageString = arguments?.getFloat("Vote average")

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

        return binding.root
    }
}