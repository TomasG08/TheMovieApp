package com.cursoandroid.themovieapp.data.remote

import com.cursoandroid.themovieapp.application.AppConstants
import com.cursoandroid.themovieapp.data.model.MovieList
import com.cursoandroid.themovieapp.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

    suspend fun getNowPlayingMovies(): MovieList = webService.getNowPlayingMovies(AppConstants.API_KEY)


}