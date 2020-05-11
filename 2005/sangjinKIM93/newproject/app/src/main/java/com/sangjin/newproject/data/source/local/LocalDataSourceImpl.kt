package com.sangjin.newproject.data.source.local

import android.app.Application
import android.content.Context
import android.util.Log
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSourceImpl : LocalDataSource{

    private val dbScope = CoroutineScope(Dispatchers.IO)

    override fun getLocalDataSource(movieDao: MovieDao): Single<List<Movie>> {
        return movieDao.getLocalMoviesData()
    }

    override fun saveLocalDataSource(movieDao: MovieDao, movies : List<Movie>) {

        dbScope.launch {
            for(movie in movies){
                movieDao.insert(movie)
                Log.d("LocalSource : ", "Local 저장 성공")
            }
        }

    }
}