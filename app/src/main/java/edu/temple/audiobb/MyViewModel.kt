package edu.temple.audiobb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val myBook: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>().also {
            loadBook()
        }
    }

    fun getBook(): LiveData<Book> {
        return myBook
    }

    fun setBook(_Book : Book){
        myBook.value = _Book

    }

    private fun loadBook() {

    }
}