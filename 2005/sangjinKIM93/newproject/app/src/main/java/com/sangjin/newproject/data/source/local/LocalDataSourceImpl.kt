package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LocalDataSourceImpl : LocalDataSource {

    private val dbScope = CoroutineScope(Dispatchers.IO)

    override fun getLocalDataSource(movieDao: MovieDao): Flowable<List<Movie>> {
        return movieDao.getLocalMoviesData()
    }

    override fun saveLocalDataSource(movieDao: MovieDao, movies: List<Movie>) {

        Completable.fromRunnable {
            Runnable {

                movieDao.insertAll(movies)

            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}