package com.sangjin.newproject.data.repository

import android.content.Context
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.ResponseData
import com.sangjin.newproject.data.source.local.MovieDao

interface NaverMovieRepository {

    interface LoadMoviesCallback{
        fun onSuccess(movies : List<Movie>)
        fun onFailure(t: Throwable)
    }

    fun getNaverMovie(query: String, callback: LoadMoviesCallback)
    fun saveLocalDataSource(movieDao: MovieDao, movies : List<Movie>)

}