package com.sangjin.newproject.data.source.local

import android.content.Context
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalDataSource {

    fun getLocalDataSource(movieDao: MovieDao) : Single<List<Movie>>
    fun saveLocalDataSource(movieDao: MovieDao, movies : List<Movie>)

}