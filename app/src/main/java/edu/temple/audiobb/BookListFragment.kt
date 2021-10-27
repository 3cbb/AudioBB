package edu.temple.audiobb

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [BookListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookListFragment : Fragment() {
    private var param1: BookList? = null


    private lateinit var layout : View

    private lateinit var recyclerView : RecyclerView

    private lateinit var bookList : BookList

    private lateinit var viewModel : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as BookList
            bookList = param1 as BookList

            viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_book_list, container, false)

        recyclerView = layout.findViewById(R.id.recyclerView)

        val onClickListener = View.OnClickListener {
            val itemPosition = recyclerView.getChildAdapterPosition(it)

            ViewModelProvider(requireActivity()).get(MyViewModel::class.java).setBook(bookList.get(itemPosition))
            (activity as EventInterface).selectionMade()
        }

        recyclerView.adapter = BookAdapter(requireActivity(), bookList, onClickListener)

        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment BookListFragment.
         */
        @JvmStatic
        fun newInstance(input: BookList) : BookListFragment {
            val fragment = BookListFragment()
            val bundle: Bundle = Bundle()
            bundle.putSerializable("param1", input)
            fragment.arguments = bundle
            return fragment
        }
    }

    interface EventInterface {
        fun selectionMade()
    }
}

class BookAdapter(var _context: Context, private val _dataSet: BookList, _ocl : View.OnClickListener) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val dataSet = _dataSet
    val ocl = _ocl

    class ViewHolder(_view: View, ocl: View.OnClickListener) : RecyclerView.ViewHolder(_view) {
        val textView = _view.findViewById<TextView>(R.id.textView4).apply { setOnClickListener(ocl) }
        val textView2 = _view.findViewById<TextView>(R.id.textView3).apply { setOnClickListener(ocl) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val constraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_item_layout, parent, false)
        return ViewHolder(constraintLayout, ocl)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet.get(position).title
        viewHolder.textView2.text = dataSet.get(position).author
    }

    override fun getItemCount() = dataSet.size()

}