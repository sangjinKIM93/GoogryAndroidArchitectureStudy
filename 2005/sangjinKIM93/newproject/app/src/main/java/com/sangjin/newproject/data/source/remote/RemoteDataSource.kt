package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.data.model.ResponseData
import io.reactivex.Single

interface RemoteDataSource {

    fun getNaverMovieRemote(query: String) : Single<ResponseData>
}