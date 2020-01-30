package edvinasnew.app.favorite

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
import edvinasnew.app.news.NewsItem
import kotlinx.android.synthetic.main.fragment_news.*

class FavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            FavoriteViewModelFactory(requireActivity().application)
        )
            .get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (requireActivity() as MainActivity).title = "Favorites"

        val adapter = FavoriteAdapter(::onNewSelected, ::onMakeArticleFavorite)

        recycler.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner, Observer { newData ->
            adapter.setItems(newData)
        })

        toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
        }
    }



    private fun onNewSelected(article: NewsItem) {
        (requireActivity() as MainActivity).showArticle(article)
    }

    private fun onMakeArticleFavorite(article: NewsItem) {
        viewModel.changeArticleFavoriteStatus(
            article
        )
        Toast.makeText(this.context, "Removed from Favorites", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): FavoriteFragment {
            val arguments = Bundle()
            val fragment = FavoriteFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}