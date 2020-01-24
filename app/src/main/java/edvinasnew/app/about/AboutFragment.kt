package edvinasnew.app.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import edvinasnew.app.news.NewsListFragment
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.toolbar
import kotlinx.android.synthetic.main.fragment_tutorial.*

class AboutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).title = "About"
        (requireActivity() as MainActivity).version.text = "Version ??.??.??"
        (requireActivity() as MainActivity).reserved.text = "2020-..."
    }

    companion object {
        fun newInstance(): AboutFragment {
            val arguments = Bundle()
            val fragment = AboutFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

}