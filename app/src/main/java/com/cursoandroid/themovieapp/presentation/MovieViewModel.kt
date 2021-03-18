package com.cursoandroid.themovieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cursoandroid.themovieapp.core.Resource
import com.cursoandroid.themovieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Quadruple(repo.getNowPlayingMovies(), repo.getUpcomingMovies(), repo.getTopRatedMovies(), repo.getPopularMovies())))

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }


}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

data class Quadruple<T1, T2, T3, T4>(val t1:T1, val t2:T2, val t3:T3, val t4:T4)