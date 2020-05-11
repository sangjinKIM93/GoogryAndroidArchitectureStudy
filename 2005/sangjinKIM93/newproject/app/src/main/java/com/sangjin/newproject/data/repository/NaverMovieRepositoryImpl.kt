package com.sangjin.newproject.data.repository

import android.content.Context
import android.util.Log
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.source.local.LocalDataSource
import com.sangjin.newproject.data.source.local.MovieDao
import com.sangjin.newproject.data.source.remote.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaverMovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val movieDao: MovieDao
) : NaverMovieRepository {

    override fun getNaverMovie(query: String, callback: NaverMovieRepository.LoadMoviesCallback) {

        //local
        localDataSource.getLocalDataSource(movieDao)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseData ->
                callback.onSuccess(responseData)
                Log.d("Repository : ", "Local 성공")
            },
                {t ->
                    callback.onFailure(t)
                })

        //remote
        remoteDataSource.getNaverMovieRemote(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseData ->
                saveLocalDataSource(movieDao, responseData.items)
                callback.onSuccess(responseData.items)
                Log.d("Repository : ", "Remote 성공")
            },
                {t ->
                    callback.onFailure(t)
                })
    }


    override fun saveLocalDataSource(movieDao: MovieDao, movies: List<Movie>) {

        localDataSource.saveLocalDataSource(movieDao, movies)

    }
}