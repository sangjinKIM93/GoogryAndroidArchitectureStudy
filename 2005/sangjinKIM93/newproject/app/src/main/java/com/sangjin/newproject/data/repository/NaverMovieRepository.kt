package com.sangjin.newproject.data.repository

import com.sangjin.newproject.adapter.ResponseData
import retrofit2.Call

interface NaverMovieRepository {

    interface LoadMoviesCallback{
        fun onResponseSuccess(responseData: ResponseData)
        fun onResponseError(message: String)
        fun onFailure(t: Throwable)
    }

    fun getNaverMovie(query: String, callback: LoadMoviesCallback)

}