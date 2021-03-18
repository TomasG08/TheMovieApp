package com.cursoandroid.themovieapp.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cursoandroid.themovieapp.R
import com.cursoandroid.themovieapp.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(binding.imageMovie)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}").centerCrop().into(binding.imageBackground)
        binding.textViewDescription.text = args.overview
        binding.textViewLanguage.text = "Language ${args.language}"
        binding.textViewTitle.text = args.title
        binding.textViewReleased.text = "Released ${args.releasedDate}"
        binding.textViewRating.text  = "${args.voteAverage} of (${args.voteCount} Reviews)"

    }
}