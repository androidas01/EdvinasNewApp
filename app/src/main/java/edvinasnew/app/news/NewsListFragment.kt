package edvinasnew.app.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import edvinasnew.app.source.SourceItem
import kotlinx.android.synthetic.main.fragment_news.*

class NewsListFragment : Fragment() {

    lateinit var viewModel: NewsViewModel

    lateinit var sourceId: String
    // var ToolbarName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ToolbarName = arguments!!.getString(KEY_SOURCE_TITLE)

        sourceId = arguments!!.getString(KEY_SOURCE_ID) ?: ""

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
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        // (requireActivity() as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        // (requireActivity() as MainActivity).title = arguments!!.getString(KEY_SOURCE_TITLE)

        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (requireActivity() as MainActivity).title = arguments!!.getString(KEY_SOURCE_TITLE)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        // val newsItemAdapter = NewsListAdapter(::onArticleSelected)

        // val adapter = NewsListAdapter(::onSourceSelected)

        val adapter = NewsListAdapter(::onNewSelected, ::onMakeArticleFavorite)

        // val adapter = NewsListAdapter(::onNewSelected, ::onMakeArticleFavorite)

        recycler.adapter = adapter
        viewModel.data.observe(this, Observer { newData ->
            adapter.setItems(newData)
        })

        toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
        }

        chip_popular_all_time.setOnClickListener {
            viewModel.onAllTimeArticlesSelected()
        }

        chip_popular_today.setOnClickListener {
            viewModel.onPopularTodayArticlesSelected()
        }

        chip_newest.setOnClickListener {
            viewModel.onNewestArticlesSelected()
        }
    }

    // fun onArticleSelected(article: NewsItem) {
    // (requireActivity() as MainActivity).showArticle(
    // article
    // )
    // }

    fun onSourceSelected(source: NewsItem) {
        (requireActivity() as MainActivity).showArticle(source)
    }

    private fun onMakeArticleFavorite(article: NewsItem) {
        viewModel.changeArticleFavoriteStatus(
            article
        )

        Toast.makeText(this.context, "Favorite", Toast.LENGTH_SHORT).show()
    }

    private fun onNewSelected(article: NewsItem) {
        (requireActivity() as MainActivity).showArticle(article)
    }

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
}