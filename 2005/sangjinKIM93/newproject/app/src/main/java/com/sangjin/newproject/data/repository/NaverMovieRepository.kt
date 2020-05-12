package com.sangjin.newproject.data.repository

import android.content.Context
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.ResponseData
import com.sangjin.newproject.data.source.local.MovieDao
import io.reactivex.Flowable
import io.reactivex.Single

interface NaverMovieRepository {

    fun getNaverMovie(query: String, success: ((List<Movie>)->Unit), fail: ((Throwable) -> Unit))
    fun refreshMovieData(query: String)
    fun saveLocalDataSource(movieDao: MovieDao, movies : List<Movie>)

}