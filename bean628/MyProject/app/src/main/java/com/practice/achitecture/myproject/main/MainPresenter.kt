package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem


class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    override fun setCacheFilePathToRepository(cacheFilePath: String) {
        NaverRepository.cacheFilePath = cacheFilePath
    }

    override fun searchIfNotEmpty(word: String, searchType: SearchType) {
        if (word.isEmpty()) {
            view.isEmpty()
        } else {
            this.searchWordByNaver(searchType, word)
        }
    }

    override fun searchWordByNaver(searchType: SearchType, word: String) {
        view.showLoading()
        NaverRepository.searchWordByNaver(
            searchType,
            word,
            object : NaverDataSource.GettingResultOfSearchingCallBack {

                override fun onSuccess(items: List<SearchedItem>) {
                    view.hideLoading()
                    when (searchType) {
                        SearchType.MOVIE, SearchType.BOOK -> {
                            view.showSearchResultMovieOrBook(items)
                        }
                        SearchType.BLOG, SearchType.NEWS -> {
                            view.showSearchResultBlogOrNews(items)
                        }
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    view.hideLoading()
                    view.searchingOnFailure(throwable)
                }
            })
    }

    override fun loadCache() {
        val lastSearchType = NaverRepository.getLastSearchType()
        if (lastSearchType != null) {
            when (lastSearchType) {
                SearchType.MOVIE, SearchType.BOOK -> {
                    view.showSearchResultMovieOrBook(NaverRepository.getCache(lastSearchType))
                }
                SearchType.BLOG, SearchType.NEWS -> {
                    view.showSearchResultBlogOrNews(NaverRepository.getCache(lastSearchType))
                }
            }
        }
    }

}