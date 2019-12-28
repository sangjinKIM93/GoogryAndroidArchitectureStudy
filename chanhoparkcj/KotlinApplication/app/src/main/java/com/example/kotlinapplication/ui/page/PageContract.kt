package com.example.kotlinapplication.ui.page

import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.data.model.MovieItem

interface PageContract {
    interface Presenter {
        fun loadData(type: Int?, query: String)
    }

    interface View {
        fun getMovie(movieItems: List<MovieItem>)
        fun getImage(imageItems: List<ImageItem>)
        fun getBlog(blogItems: List<BlogItem>)
        fun getKin(kinItems: List<KinItem>)
        fun getError(message: String)
    }
}