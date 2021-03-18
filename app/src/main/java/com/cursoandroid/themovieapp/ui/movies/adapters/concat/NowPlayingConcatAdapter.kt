package com.cursoandroid.themovieapp.ui.movies.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.themovieapp.core.BaseConcatHolder
import com.cursoandroid.themovieapp.databinding.NowPlayingMovieRowBinding
import com.cursoandroid.themovieapp.databinding.PopularMovieRowBinding
import com.cursoandroid.themovieapp.ui.movies.adapters.MovieAdapter

class NowPlayingConcatAdapter (private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = NowPlayingMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: NowPlayingMovieRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.recyclerViewNowPlayingMovies.adapter = adapter
        }
    }
}