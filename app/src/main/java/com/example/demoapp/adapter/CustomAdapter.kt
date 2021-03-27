package com.example.demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.model.Book

class CustomAdapter(private val clickedItem: CustomItemClicked) : RecyclerView.Adapter<CustomAdapter.CustomItemHolder>() {

    private var listItems = mutableListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CustomItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: CustomItemHolder, position: Int) {
        // get book object
        val book = listItems[position]

        holder.textView.text = book.title
        Glide.with(holder.itemView).load(book.imageURL).into(holder.imageView)
    }

    fun setData(books: List<Book>) {
        listItems.addAll(books)
        notifyDataSetChanged()
    }

    inner class CustomItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.listing_tv)
        var imageView: ImageView = itemView.findViewById(R.id.listing_iv)

        // always set on click listeners in the the inner class
        // instead of onBindViewHolder as this is a one time operation
        init {
            itemView.setOnClickListener {
                clickedItem.onClick(listItems[adapterPosition])
            }
        }
    }

    interface CustomItemClicked {
        fun onClick(book: Book)
    }
}