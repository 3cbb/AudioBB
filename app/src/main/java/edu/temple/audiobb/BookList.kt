package edu.temple.audiobb

import java.io.Serializable

class BookList(): Serializable {
    var myBookList: List<Book> = listOf()


    fun add(_book: Book){
        var tempList: List<Book> = listOf(_book)
        myBookList = myBookList + tempList
    }

    fun add(_listIn: List<Book>){
        myBookList = myBookList + _listIn
    }

    fun remove(_book: Book){
        var tempList: List<Book> = listOf(_book)
        myBookList = myBookList - tempList
    }

    fun remove(_listIn: List<Book>){
        myBookList = myBookList - _listIn
    }

    fun get(_index: Int): Book{
        var tempBook: Book
        if (-1 < _index && _index < size()){
            tempBook = myBookList[_index]
        } else {
            tempBook = Book("No Book at index$_index", "Error")
        }

        return tempBook
    }

    fun size() : Int{
        return myBookList.size
    }
}