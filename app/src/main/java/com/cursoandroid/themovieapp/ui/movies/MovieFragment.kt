package com.cursoandroid.themovieapp.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.cursoandroid.themovieapp.R
import com.cursoandroid.themovieapp.core.Resource
import com.cursoandroid.themovieapp.data.local.AppDataBase
import com.cursoandroid.themovieapp.data.local.LocalMovieDataSource
import com.cursoandroid.themovieapp.data.model.Movie
import com.cursoandroid.themovieapp.data.remote.RemoteMovieDataSource
import com.cursoandroid.themovieapp.databinding.FragmentMovieBinding
import com.cursoandroid.themovieapp.presentation.MovieViewModel
import com.cursoandroid.themovieapp.presentation.MovieViewModelFactory
import com.cursoandroid.themovieapp.repository.MovieRepositoryImpl
import com.cursoandroid.themovieapp.repository.RetrofitClient
import com.cursoandroid.themovieapp.ui.movies.adapters.MovieAdapter
import com.cursoandroid.themovieapp.ui.movies.adapters.concat.NowPlayingConcatAdapter
import com.cursoandroid.themovieapp.ui.movies.adapters.concat.PopularConcatAdapter
import com.cursoandroid.themovieapp.ui.movies.adapters.concat.TopRatedConcatAdapter
import com.cursoandroid.themovieapp.ui.movies.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {


    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webService),
                LocalMovieDataSource(AppDataBase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()


        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            NowPlayingConcatAdapter(
                                MovieAdapter(
                                    result.data.t1.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.t2.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.t3.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            3,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.t4.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    binding.recyclerViewMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    //Log.d("Error", "${result.exception}")
                    print("Error!! -> ${result.exception}")
                    //Agregando cambios al repositorio salu2
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(
            movie.poster_path, movie.backdrop_path, movie.vote_average.toFloat(), movie.vote_count,
            movie.overview, movie.title, movie.original_language, movie.release_date, movie.video
        )
        findNavController().navigate(action)
    }
}