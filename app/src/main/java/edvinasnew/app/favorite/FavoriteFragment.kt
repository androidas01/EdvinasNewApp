package edvinasnew.app.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import edvinasnew.app.news.NewsItem
import kotlinx.android.synthetic.main.fragment_news.*

class FavoriteFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).title = "Favorites"

    }

//    private fun onNewSelected(article: NewsItem) {
//        (requireActivity() as MainActivity).showArticle(article)
//    }

    companion object {
        fun newInstance(): FavoriteFragment {
            val arguments = Bundle()
            val fragment = FavoriteFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

}