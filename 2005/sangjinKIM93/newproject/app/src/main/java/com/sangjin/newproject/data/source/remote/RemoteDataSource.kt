package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.adapter.ResponseData
import retrofit2.Call

interface RemoteDataSource {

    interface LoadMoviesRemoteCallback{
        fun onResponseSuccess(responseData: ResponseData)
        fun onResponseError(message: String)
        fun onFailure(t: Throwable)
    }

    fun getNaverMovieRemote(query : String, callback: LoadMoviesRemoteCallback)
}