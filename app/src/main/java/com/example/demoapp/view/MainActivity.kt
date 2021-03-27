package com.example.demoapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.adapter.CustomAdapter
import com.example.demoapp.R
import com.example.demoapp.model.Book
import com.example.demoapp.viewmodel.BookResponseViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple MVVM example to retrieve a list of books present at
 * "http://de-coding-test.s3.amazonaws.com/books.json"
 */

class MainActivity : AppCompatActivity(), CustomAdapter.CustomItemClicked {

    private lateinit var bookResponseViewModel: BookResponseViewModel
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customAdapter = CustomAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        bookResponseViewModel = ViewModelProvider(this).get(BookResponseViewModel::class.java)

        bookResponseViewModel.getBookResponseLiveData()
                .observe(this, {
                    customAdapter.setData(books = it)
                })

        // start network call
        bookResponseViewModel.searchBooks()
    }

    override fun onClick(book: Book) {
        Log.e("demo", "clicked on book - " + book.title)
    }
}