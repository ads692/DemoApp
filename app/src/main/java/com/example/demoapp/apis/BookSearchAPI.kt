package com.example.demoapp.apis

import com.example.demoapp.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface BookSearchAPI {
    @GET ("/books.json")
    fun getBooks() : Call<List<Book>>
}