package com.example.kotlinapplication.ui.home

import android.os.Bundle
import android.view.View
import com.example.kotlinapplication.R
import com.example.kotlinapplication.base.ui.BaseFragment
import com.example.kotlinapplication.ui.page.PageFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_home.adapter = HomePagerAdapter(fragmentManager!!).apply {
            addFragment(PageFragment.newInstance(PageFragment.MOVIE_VIEW), "영화")
            addFragment(PageFragment.newInstance(PageFragment.IMAGE_VIEW), "이미지")
            addFragment(PageFragment.newInstance(PageFragment.BLOG_VIEW), "블로그")
            addFragment(PageFragment.newInstance(PageFragment.KIN_VIEW), "지식인")
        }
        tl_home.setupWithViewPager(vp_home)
    }

}
