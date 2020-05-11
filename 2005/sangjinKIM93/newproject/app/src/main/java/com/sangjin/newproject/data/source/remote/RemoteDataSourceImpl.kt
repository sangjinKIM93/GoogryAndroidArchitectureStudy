package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.MovieApi
import com.sangjin.newproject.data.model.ResponseData
import io.reactivex.Single

class RemoteDataSourceImpl : RemoteDataSource {

    override fun getNaverMovieRemote(query: String): Single<ResponseData> {

        return MovieApi.retrofitService.requestMovieList(query)

    }
}