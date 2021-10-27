package edu.temple.audiobb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), BookListFragment.EventInterface {


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
            && !myViewModel.isEmptyorNull())
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
        } else if(!myViewModel.isEmptyorNull()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, BookDetailsFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun selectionMade() {

        if (!twoFragments)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, BookDetailsFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        myViewModel.setBook(Book("",""))
    }


}