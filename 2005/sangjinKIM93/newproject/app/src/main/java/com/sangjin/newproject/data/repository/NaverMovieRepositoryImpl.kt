package com.sangjin.newproject.data.repository

import com.sangjin.newproject.adapter.ResponseData
import com.sangjin.newproject.data.source.remote.RemoteDataSource

class NaverMovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : NaverMovieRepository {

    override fun getNaverMovie(query: String, callback: NaverMovieRepository.LoadMoviesCallback) {
        remoteDataSource.getNaverMovieRemote(query, object: RemoteDataSource.LoadMoviesRemoteCallback{
            override fun onResponseSuccess(responseData: ResponseData) {
                callback.onResponseSuccess(responseData)
            }

            override fun onResponseError(message: String) {
                callback.onResponseError(message)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }

        })
    }
}