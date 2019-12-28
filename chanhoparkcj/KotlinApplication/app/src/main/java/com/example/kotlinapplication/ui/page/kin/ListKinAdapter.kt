package com.example.kotlinapplication.ui.page.kin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.KinItem

class ListKinAdapter(
    val listener: ItemListener

) :
    RecyclerView.Adapter<KinViewHolder>() {

    private val items = arrayListOf<KinItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinViewHolder {
        return KinViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_kin_list,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: KinViewHolder, position: Int) {
        holder.bind(items[position], listener)

    }

    fun addAllItems(ListItems: List<KinItem>) {
        items.clear()
        items.addAll(ListItems)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onKinItemClick(kinItems: KinItem)
    }


}