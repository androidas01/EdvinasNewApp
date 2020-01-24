package edvinasnew.app.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import edvinasnew.app.R
import edvinasnew.app.about.AboutFragment
import edvinasnew.app.article.ArticleFragment
import edvinasnew.app.favorite.FavoriteFragment
import edvinasnew.app.news.NewsItem
import edvinasnew.app.news.NewsListFragment
import edvinasnew.app.source.SourceItem
import edvinasnew.app.source.SourceListFragment
import edvinasnew.app.tutorial.TutorialActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            onBottomNavigationEvent(it)
        }

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

//        setSupportActionBar(toolbar)
//        actionBar?.setDisplayShowHomeEnabled(true)
//        title = "Source List"

//        findViewById<Button>(R.id.bottom_navigation_source).setOnClickListener {
//            //            supportFragmentManager.beginTransaction()
////                .replace(R.id.container, TutorialActivity)
////                .commit()
//            //Toast.makeText(this.context, "gggg", Toast.LENGTH_LONG).show()
//
//        }


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

    fun showArticle(source: NewsItem) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, ArticleFragment.newInstance(source))
            .commit()
    }

    fun showSort(source: NewsItem) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, ArticleFragment.newInstance(source))
            .commit()
    }

    private fun showAbout() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, AboutFragment.newInstance())
            .commit()
    }

    private fun showFavorite() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, FavoriteFragment.newInstance())
            .commit()
    }

    private fun onBottomNavigationEvent(it: MenuItem): Boolean {
        return when (it.itemId) {
            R.id.bottom_navigation_source -> {
                this.showSource()
                true
            }
            R.id.bottom_navigation_favorite -> {
                this.showFavorite()
                true
            }
            else -> {
                this.showAbout()
                true
            }
        }
    }
}


