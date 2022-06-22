package com.cursoandroid.themovieapp.ui.moviedetails

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.MediaController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cursoandroid.themovieapp.R
import com.cursoandroid.themovieapp.application.AppConstants
import com.cursoandroid.themovieapp.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        bindView()
    }

    private fun bindView(){
        Glide.with(requireContext()).load("${AppConstants.IMAGE_URL}${args.posterImageUrl}")
            .centerCrop().into(binding.imageMovie)
        Glide.with(requireContext())
            .load("${AppConstants.IMAGE_URL}${args.backgroundImageUrl}").centerCrop()
            .into(binding.imageBackground)
        binding.textViewDescription.text = args.overview
        binding.textViewLanguage.text = getString(
            R.string.language,
            args.language
        )
        binding.textViewTitle.text = args.title
        binding.textViewReleased.text =
            getString(R.string.released, args.releasedDate)
        binding.textViewRating.text = getString(
            R.string.rating_average,
            args.voteAverage.toString(),
            args.voteCount.toString()
        )

    }
}