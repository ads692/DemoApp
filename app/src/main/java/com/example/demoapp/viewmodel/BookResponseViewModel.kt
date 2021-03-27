package com.example.demoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.demoapp.model.Book
import com.example.demoapp.repository.WebSearchRepository

class BookResponseViewModel(application: Application) : AndroidViewModel(application) {

    init {
        webSearchRepository = WebSearchRepository()
        bookResponseLiveData = webSearchRepository.getBookResponseData()
    }

    fun searchBooks() {
        return webSearchRepository.searchBooks()
    }

    fun getBookResponseLiveData() = bookResponseLiveData

    companion object {
        private lateinit var webSearchRepository: WebSearchRepository
        private lateinit var bookResponseLiveData: LiveData<List<Book>>
    }
}