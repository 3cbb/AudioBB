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

    fun isEmptyorNull() : Boolean{
        var isEmptyorNull = false
        var authorEmpty = false
        var titleEmpty = false

        if(myBook.value == null){
            isEmptyorNull = true
        } else {
            if (myBook.value!!.author == "" || myBook.value!!.author.startsWith("Error")) {
                authorEmpty = true
            }
            if (myBook.value!!.title == "" || myBook.value!!.title.startsWith("No Book at index")) {
                titleEmpty = true
            }
        }

        if(authorEmpty && titleEmpty){
            isEmptyorNull = true
        }

    return isEmptyorNull
    }
}