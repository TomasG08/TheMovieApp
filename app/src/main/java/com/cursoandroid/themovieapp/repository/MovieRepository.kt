package com.cursoandroid.themovieapp.repository

import com.cursoandroid.themovieapp.data.model.MovieList

interface MovieRepository {

    suspend fun getNowPlayingMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getUpcomingMovies(): MovieList
}