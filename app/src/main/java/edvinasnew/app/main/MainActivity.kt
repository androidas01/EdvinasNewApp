package edvinasnew.app.main

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import edvinasnew.app.R
import edvinasnew.app.news.NewsListFragment
import edvinasnew.app.source.SourceItem
import edvinasnew.app.source.SourceListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(application)
        )
            .get(MainViewModel::class.java)

//        viewModel.showTutorial.observe(this, Observer { newData ->
//            this.showTutorial()
//        })

        if (savedInstanceState == null) {
            this.showSource()
        }
        //this.showAbout()

        setSupportActionBar(toolbar)
        actionBar?.setDisplayShowHomeEnabled(true)
        title = "Source List"


    }

//    private fun showTutorial() {
//        startActivity(Intent(this, TutorialActivity::class.java))
//    }

    private fun showSource() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SourceListFragment.newInstance())
            .commit()
    }

    fun showNews(source: SourceItem) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, NewsListFragment.newInstance(source))
            .commit()
    }

}
