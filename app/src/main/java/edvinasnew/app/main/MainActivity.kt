package edvinasnew.app.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edvinasnew.app.news.NewsListFragment
import edvinasnew.app.R
import edvinasnew.app.source.Source
import edvinasnew.app.source.SourceListFragment
import edvinasnew.app.tutorial.TutorialActivity


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivity(Intent(this, TutorialActivity::class.java))

//        recycler.layoutManager = LinearLayoutManager(this)
//        recycler.adapter

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(application))
            .get(MainViewModel::class.java)

        viewModel.showTutorial.observe(this, Observer { x ->
            startActivity(Intent(this, TutorialActivity::class.java))
        })

        if (savedInstanceState == null) {
            //this.showSource()
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                SourceListFragment()
            )
                .commit()
        }


    }




    fun showNewsList(source: Source) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                NewsListFragment.newInstance(source)
            )
            .commit()
    }
}
