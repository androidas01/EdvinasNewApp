package edvinasnew.app.source

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import kotlinx.android.synthetic.main.fragment_source.*

class SourceListFragment : Fragment() {

    lateinit var viewModel: SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            SourceViewModelFactory(requireActivity().application)
        )
            .get(SourceViewModel::class.java)
            setHasOptionsMenu(true) // menu add search #1
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { // menu add search #2
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.source_fragment_menu, menu)

        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onSearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        menu.findItem(R.id.action_search)
            .setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    viewModel.onCreate()
                    return true
                }
            })
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_source, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val adapter = SourceListAdapter(::onSourceSelected)

        // val adapter = SourceListAdapter(object :
        // OnSourceSelectedListener {
        // override fun onSourceSelected(sourceItem: SourceItem) {
        // this@SourceListFragment.onSourceSelected(sourceItem)
        // }
        // })
        recycler.adapter = adapter
        viewModel.data.observe(this, Observer { newData ->
            adapter.setItems(newData)
        })

        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).title = "Source List"

        view.findViewById<Button>(R.id.sort).setOnClickListener {
            // (requireActivity() as SourceViewModel).sort()
            // Toast.makeText(this.context, "gggg", Toast.LENGTH_LONG).show()
            val checksort = viewModel.sort()
            if (checksort == false) {
                (requireActivity() as MainActivity).sort.text = "SORT A -> Z"
            } else {
                (requireActivity() as MainActivity).sort.text = "SORT Z -> A"
            }
        }
    }

    fun onSourceSelected(source: SourceItem) {
        (requireActivity() as MainActivity).showNews(source)
    }

    companion object {
        fun newInstance() =
            SourceListFragment()
    }
}