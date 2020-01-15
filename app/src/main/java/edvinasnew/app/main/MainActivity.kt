package edvinasnew.app.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edvinasnew.app.news.NewsListFragment
import edvinasnew.app.R
import edvinasnew.app.article.ArticleFragment
import edvinasnew.app.news.NewsItem
import edvinasnew.app.source.SourceItem
import edvinasnew.app.source.SourceListFragment
import edvinasnew.app.tutorial.TutorialActivity


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
            .replace(R.id.container, NewsListFragment.newInstance(source.id))
            .commit()
    }

   }
