package edvinasnew.app.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import edvinasnew.app.news.NewsItem
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_article.view.*

class ArticleFragment : Fragment() {

    lateinit var viewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val article = arguments!!.getParcelable<NewsItem>(KEY_ARTICLE)

        viewModel = ViewModelProviders.of(
            this,
            ArticleViewModelFactory(
                requireActivity().application,
                article!!
            )
        )
            .get(ArticleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_article, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.data.observe(this, Observer { newData ->
            // article_title.text = (newData as NewsItem).title

            Glide.with(view)
                .load((newData as NewsItem).urlToImage)
                .placeholder(R.drawable.loading)
                .into(view.Nuotrauka)

            Title.text = (newData as NewsItem).title

            if ((newData as NewsItem).description == "") {
                Description.visibility = View.GONE
            } else {
                Description.text = (newData as NewsItem).description
            }

            Author.text = (newData as NewsItem).author

            Date.text = (newData as NewsItem).date

            view.article_readFull.setOnClickListener {
                val uri: Uri =
                    Uri.parse(newData.url) // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

            // (newData as NewsItem).urlToImage

            // article_description.text = newData.description
            // article_author.text = newData.author
            // article_date.text = newData.publishedAt.toString()
            // article_readFull.setOnClickListener{

            // val uri: Uri =
            // Uri.parse(newData.urlToImage) // missing 'http://' will cause crashed
            // val intent = Intent(Intent.ACTION_VIEW, uri)
            // startActivity(intent)
            // }
        })
    }

    companion object {
        const val KEY_ARTICLE = "articleObject"

        fun newInstance(
            article: NewsItem
        ): ArticleFragment {
            val arguments = Bundle()
            arguments.putParcelable(KEY_ARTICLE, article)
            val fragment =
                ArticleFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}