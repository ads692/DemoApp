package com.example.demoapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.demoapp.apis.BookSearchAPI
import com.example.demoapp.model.Book
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WebSearchRepository {
    init {
        bookResponseLiveData = MutableLiveData()

        bookSearchAPI = Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(BookSearchAPI::class.java)
    }

    // ACTUAL NETWORK CALL TO GET BOOKS
    fun searchBooks() {
        bookSearchAPI.getBooks().enqueue(object: Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    bookResponseLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                bookResponseLiveData.postValue(null)
            }
        })
    }

    fun getBookResponseLiveData(): MutableLiveData<List<Book>> = bookResponseLiveData

    companion object {
        private const val BOOK_SEARCH_BASE_URL = "http://de-coding-test.s3.amazonaws.com"
        private lateinit var bookSearchAPI: BookSearchAPI
        private lateinit var bookResponseLiveData: MutableLiveData<List<Book>>
        private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}