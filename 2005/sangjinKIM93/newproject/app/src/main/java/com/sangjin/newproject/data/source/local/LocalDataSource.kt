package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie
import io.reactivex.Flowable

interface LocalDataSource {

    fun getLocalDataSource(movieDao: MovieDao) : Flowable<List<Movie>>
    fun saveLocalDataSource(movieDao: MovieDao, movies : List<Movie>)

}