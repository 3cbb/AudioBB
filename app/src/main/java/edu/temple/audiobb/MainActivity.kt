package edu.temple.audiobb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    lateinit var myViewModel: MyViewModel

    var twoFragments = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myBookList = BookList()

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)


        twoFragments = findViewById<View>(R.id.fragmentContainerView2) != null

        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    is BookDetailsFragment
            && myViewModel.getBook().value != null)
            supportFragmentManager.popBackStack()

        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) is BookDetailsFragment
            && twoFragments)
            supportFragmentManager.popBackStack()

        myBookList.add(listOf(Book("Homestuck Act 1", "Andrew Hussie"),Book("Homestuck Act 2", "Andrew Hussie"),Book("Homestuck Act 5 Act 2 Part 1", "Andrew Hussie"),Book("Gunnerkrigg Court Vol. 1", "Tom Siddell"),Book("xkcd: What If", "Randall Monroe"),Book("Housepets!", "Rick Griffin"),Book("Dinosaur Comics", "Ryan North"),Book("Girl Genius", "Phil and Kaja Foglio"),Book("Kill Six Billion Demons","Tom Parkinson-Morgan"), Book("Hyperbole and a Half", "Allie Brosh")))

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, BookListFragment.newInstance(myBookList))
                .commit()






        if (twoFragments) {
            if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) == null)
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerView2, BookDetailsFragment())
                    .commit()
        } else if(myViewModel.getBook().value != null) { // If moving to single-pane
            supportFragmentManager.beginTransaction()                 // but a color was selected
                .add(R.id.fragmentContainerView, BookDetailsFragment())              // before the switch
                .addToBackStack(null)
                .commit()
        }
    }

    override fun selectionMade() {
        // only respond if there is a single container
        if (!twoFragments)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, BookDetailsFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // A single way to "clear" the selected book so that
        // if doesn't remain selected. Remove it when the user
        // hits Back
        myViewModel.setBook(Book("",""))
    }


}