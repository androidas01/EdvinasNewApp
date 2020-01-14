package edvinasnew.app.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val adapter = SourceListItemeAdapter(object :
            OnSourceSelectedListener {
            override fun onSourceSelected(source: Source) {
                this@SourceListFragment.onSourceSelected(source)
            }
        })
    recycler.adapter = adapter
        viewModel.data.observe(this, Observer { newData ->
            adapter.setItems(newData)
        })
    }

    fun onSourceSelected(source: Source) {
        (requireActivity() as MainActivity).showNewsList(source)
    }
    }


interface OnSourceSelectedListener {
    fun onSourceSelected(source: Source)
}