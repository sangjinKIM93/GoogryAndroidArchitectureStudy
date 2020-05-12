package com.sangjin.newproject.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.source.local.LocalDataSource
import com.sangjin.newproject.data.source.local.MovieDao
import com.sangjin.newproject.data.source.remote.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaverMovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val movieDao: MovieDao
) : NaverMovieRepository {

    @SuppressLint("CheckResult")
    override fun getNaverMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ){
        //local cache
        localDataSource.getLocalDataSource(movieDao)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseData ->
                success(responseData)
                Log.d("Repository : ", "Local 성공")
            },
                { t ->
                    fail(t)
                })




    }

    @SuppressLint("CheckResult")
    override fun refreshMovieData(query: String) {
        //remote
        remoteDataSource.getNaverMovieRemote(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseData ->

                saveLocalDataSource(movieDao, responseData.items)

            },
                { t ->

                })
    }


    override fun saveLocalDataSource(movieDao: MovieDao, movies: List<Movie>) {

        localDataSource.saveLocalDataSource(movieDao, movies)

    }
}