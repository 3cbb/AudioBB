package edu.temple.audiobb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

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

        val request = JsonArrayRequest(Request.Method.GET, searchURL, null, Response.Listener {
                response ->try {
            for (i in 0 until response.length()) {
                val book = response.getJSONObject(i)
                val title = book.getString("title")
                val id = book.getInt("id")
                val coverURL = book.getString("cover_url")
                val author = book.getString("author")
                bookList = bookList + Book(title, author, id, coverURL)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })






        requestQueue.add(request)


        return bookList
    }




}