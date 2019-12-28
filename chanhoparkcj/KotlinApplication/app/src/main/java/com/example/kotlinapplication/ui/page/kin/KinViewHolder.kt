package com.example.kotlinapplication.ui.page.kin

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.data.model.KinItem
import kotlinx.android.synthetic.main.item_kin_list.view.*

class KinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: KinItem, listener: ListKinAdapter.ItemListener?) {
        with(itemView) {
            kin_item_layout.setOnClickListener {
                listener?.let {
                    it.onKinItemClick(item)
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                kin_item_title.text = Html.fromHtml(item.title, 0)
                kin_item_description.text = Html.fromHtml(item.description, 0)
            } else {
                kin_item_title.text = item.title
                kin_item_description.text = item.description
            }
        }
    }

}