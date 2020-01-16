package edvinasnew.app.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import edvinasnew.app.source.SourceItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_tutorial.*


//import kotlinx.android.synthetic.main.fragment_source.*

class NewsListFragment() : Fragment() {

    lateinit var viewModel: NewsViewModel

    lateinit var sourceId: String
    //var ToolbarName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ToolbarName = arguments!!.getString(KEY_SOURCE_TITLE)

        sourceId = arguments!!.getString(KEY_SOURCE_ID)?: ""

        viewModel = ViewModelProviders.of(
            this,
            NewsViewModelFactory(requireActivity().application, sourceId)
        )
            .get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
//        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
//        (requireActivity() as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).title = arguments!!.getString(KEY_SOURCE_TITLE)




        recycler.layoutManager = LinearLayoutManager(requireContext())
        //val newsItemAdapter = NewsListAdapter(::onArticleSelected)

//        viewModel.data.observe(this, Observer { newData ->
//            newsItemAdapter.setItems(newData)
//        })
//
//        recycler.adapter = newsItemAdapter
//        val adapter = NewsListItemAdapter()
//        recycler.adapter = adapter
//        adapter.setItems(
//            listOf<SourceArticle>(
//                SourceArticle(
//                    "test asddddddddddddddddddddddddddddas sda ",
//                    "description test",
//                    "",//R.drawable.new2,
//                    "2020-01-10",
//                    "",
//                    ""
//                )
//            )
        //)

        val adapter = NewsListAdapter()
        recycler.adapter = adapter
        viewModel.data.observe(this, Observer { newData ->
            adapter.setItems(newData)
        })

        chip_popular_all_time.setOnClickListener{
            viewModel.onAllTimeArticlesSelected()
        }
    }

//    fun onArticleSelected(article: NewsItem) {
//        (requireActivity() as MainActivity).showArticle(
//            article
//        )
//    }
    companion object {
        private const val KEY_SOURCE_TITLE = "key_source_title"
        private const val KEY_SOURCE_ID = "key_source_id"

        fun newInstance(sourceItem: SourceItem): NewsListFragment {
            val arguments = Bundle()
            arguments.putString(KEY_SOURCE_TITLE, sourceItem.title)
            arguments.putString(KEY_SOURCE_ID, sourceItem.id)
            val fragment = NewsListFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

//    companion object {
//        fun newInstance(id: String) = NewsListFragment(id)
//    }


}