package edu.temple.audiobb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class BookSearchActivity : AppCompatActivity() {

    lateinit var bookList : List<Book>
    lateinit var requestQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        var button: Button = findViewById(R.id.button)
        var editText = findViewById<EditText>(R.id.editTextTextSearchString)

        requestQueue = Volley.newRequestQueue(this)

        button.setOnClickListener{
            bookList = search(editText.text.toString())
        }


    }

    fun search(_index: String) : List<Book>{
        var bookList: List<Book> = listOf()
        var searchURL = "https://kamorris.com/lab/cis3515/search.php?term=$_index"
        var tempString : String

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, searchURL, null, { response ->
            tempString = "Response: %s".format(response.toString())
        }, { error ->
            // TODO: Handle error
        })

        requestQueue.add(jsonObjectRequest)


        return bookList
    }




}