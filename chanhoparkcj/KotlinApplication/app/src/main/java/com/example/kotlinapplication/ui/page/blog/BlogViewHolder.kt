package com.example.kotlinapplication.ui.page.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.extension.getHtmlText
import kotlinx.android.synthetic.main.item_blog_list.view.*

class BlogViewHolder(parent: ViewGroup, private val listener: (BlogItem) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog_list, parent, false)
    ) {

    private lateinit var item: BlogItem

    init {
        itemView.setOnClickListener {
            listener(item)
        }
    }

    fun bind(item: BlogItem) {
        this.item = item
        with(itemView) {
            tv_title.text = item.title.getHtmlText()
            tv_bloggername.text = item.bloggername.getHtmlText()
            tv_bloggerlink.text = item.bloggerlink.getHtmlText()
            tv_description.text = item.description.getHtmlText()
        }
    }
}