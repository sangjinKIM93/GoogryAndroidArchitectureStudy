package com.example.kotlinapplication.ui.page

import com.example.kotlinapplication.data.repository.DataRepositoryImpl

class PagePresenter(listener: PageContract.View) :
    PageContract.Presenter {
    private val view: PageContract.View = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    override fun loadData(type: Int?, query: String) {
        when (type) {
            PageFragment.MOVIE_VIEW -> dataRepositoryImpl.callMovieResources(query).subscribe(
                { datas -> view.getMovie(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            PageFragment.IMAGE_VIEW -> dataRepositoryImpl.callImageResources(query).subscribe(
                { datas -> view.getImage(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            PageFragment.BLOG_VIEW -> dataRepositoryImpl.callBlogResources(query).subscribe(
                { datas -> view.getBlog(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            PageFragment.KIN_VIEW -> dataRepositoryImpl.callKinResources(query).subscribe(
                { datas -> view.getKin(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            else -> {
                view.getError("에러")
            }
        }
    }

}