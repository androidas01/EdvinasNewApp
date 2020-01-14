package edvinasnew.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*

//import kotlinx.android.synthetic.main.fragment_source.*

class NewsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val config = arguments!!.getParcelable<Source>(
            NewsListFragment.KEY_ARTICLE
        )!!
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val adapter = NewsListItemAdapter()
        recycler.adapter = adapter
        adapter.setItems(
            listOf<SourceArticle>(
                SourceArticle(
                    "test asddddddddddddddddddddddddddddas sda ",
                    "description test",
                     R.drawable.new2,
                    "2020-01-10"
                )
            )
        )
    }

    companion object {
        private const val KEY_ARTICLE = "key_article"

        fun newInstance(source: Source): NewsListFragment {
            val arguments = Bundle()
            arguments.putParcelable(KEY_ARTICLE, source)
            val fragment = NewsListFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}