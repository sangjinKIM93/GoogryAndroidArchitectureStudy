package com.example.kotlinapplication.ui.page

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.base.ui.BaseFragment
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.data.model.MovieItem
import com.example.kotlinapplication.ui.page.blog.ListBlogAdapter
import com.example.kotlinapplication.ui.page.image.ListImageAdapter
import com.example.kotlinapplication.ui.page.kin.ListKinAdapter
import com.example.kotlinapplication.ui.page.movie.ListMovieAdapter
import kotlinx.android.synthetic.main.fragment_page.*


class PageFragment : BaseFragment(R.layout.fragment_page),
    ListMovieAdapter.ItemListener,
    ListImageAdapter.ItemListener,
    ListKinAdapter.ItemListener,
    PageContract.View {

    private lateinit var movieAdapter: ListMovieAdapter
    private lateinit var blogAdapter: ListBlogAdapter
    private lateinit var imageAdapter: ListImageAdapter
    private lateinit var kinAdapter: ListKinAdapter

    private var type: Int? = null
    private lateinit var presenter: PagePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpButtonClickListener()
    }

    private fun start() {
        presenter = PagePresenter(this)
        type = arguments?.getInt(EXTRA_MESSAGE)

        when (type) {
            MOVIE_VIEW -> {
                home_search_btn.text = "영화 검색"
                movieAdapter = ListMovieAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = movieAdapter
                }
            }
            IMAGE_VIEW -> {
                home_search_btn.text = "이미지 검색"
                imageAdapter = ListImageAdapter(this)
                with(home_recyclerview) {
                    layoutManager = GridLayoutManager(activity, 4)
                    adapter = imageAdapter
                }
            }
            BLOG_VIEW -> {
                home_search_btn.text = "블로그 검색"
                blogAdapter =
                    ListBlogAdapter(this::onBlogItemClick)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = blogAdapter
                }
            }
            KIN_VIEW -> {
                home_search_btn.text = "지식인 검색"
                kinAdapter = ListKinAdapter(this)
                with(home_recyclerview) {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                    adapter = kinAdapter
                }
            }
        }
    }

    private fun onBlogItemClick(blogItem: BlogItem) {
        toast(blogItem.link)
        webLink(blogItem.link)
    }

    private fun setUpButtonClickListener() {
        home_search_btn.setOnClickListener {
            if (home_search_edit.text.isBlank()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${home_search_edit.text}")
                presenter.loadData(type, home_search_edit.text.toString())
            }
        }

    }

    override fun onMovieItemClick(movieItems: MovieItem) {
        toast(movieItems.link)
        webLink(movieItems.link)
    }

    override fun onImageItemClick(imageItems: ImageItem) {
        toast(imageItems.link)
        webLink(imageItems.link)
    }

    override fun onKinItemClick(kinItems: KinItem) {
        toast(kinItems.link)
        webLink(kinItems.link)
    }

    override fun getMovie(movieItems: List<MovieItem>) {
        movieAdapter.addAllItems(movieItems)
    }

    override fun getImage(imageItems: List<ImageItem>) {
        imageAdapter.addAllItems(imageItems)
    }

    override fun getBlog(blogItems: List<BlogItem>) {
        blogAdapter.resetItems(blogItems)
    }

    override fun getKin(kinItems: List<KinItem>) {
        kinAdapter.addAllItems(kinItems)
    }

    override fun getError(message: String) {
        toast(message)
    }

    companion object {

        const val MOVIE_VIEW = 0
        const val IMAGE_VIEW = 1
        const val BLOG_VIEW = 2
        const val KIN_VIEW = 3

        fun newInstance(message: Int): PageFragment {
            val pageFragment = PageFragment()
            val bundle = Bundle(1)
            bundle.putInt(EXTRA_MESSAGE, message)
            pageFragment.arguments = bundle
            return pageFragment
        }
    }
}
