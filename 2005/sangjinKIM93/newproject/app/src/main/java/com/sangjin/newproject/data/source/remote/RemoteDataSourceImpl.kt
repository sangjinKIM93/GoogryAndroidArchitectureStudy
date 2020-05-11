package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.MovieApi
import com.sangjin.newproject.adapter.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    override fun getNaverMovieRemote(
        query: String,
        callback: RemoteDataSource.LoadMoviesRemoteCallback
    ) {
        MovieApi.retrofitService.requestMovieList(query).enqueue(object : Callback<ResponseData> {

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val body = response.body()
                if(body != null && response.isSuccessful){
                    callback.onResponseSuccess(body)
                } else {
                    callback.onResponseError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                callback.onFailure(t)
            }

        })
    }
}