package edu.temple.audiobb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myBookList = BookList()

        myBookList.add(listOf(Book("Homestuck Act 1", "Andrew Hussie"),Book("Homestuck Act 2", "Andrew Hussie"),Book("Homestuck Act 5 Act 2 Part 1", "Andrew Hussie"),Book("Gunnerkrigg Court Vol. 1", "Tom Siddell"),Book("xkcd: What If", "Randall Monroe"),Book("Housepets!", "Rick Griffin"),Book("Dinosaur Comics", "Ryan North"),Book("Girl Genius", "Phil and Kaja Foglio"),Book("Kill Six Billion Demons","Tom Parkinson-Morgan"), Book("Hyperbole and a Half", "Allie Brosh")))
    }


}