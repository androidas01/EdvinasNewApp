package edvinasnew.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import edvinasnew.app.tutorial.TutorialActivity
import edvinasnew.app.tutorial.TutorialScreenConfig
import edvinasnew.app.tutorial.TutorialViewModel
import edvinasnew.app.tutorial.TutorialViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: TutorialViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, TutorialViewModelFactory(application))
            .get(TutorialViewModel::class.java)
        //startActivity(Intent(this, TutorialActivity::class.java))
        //startActivity(Intent(this, TutorialActivity ::class.java))
        viewModel.showTutorial.observe(this, Observer { newData ->
            startActivity(Intent(this, TutorialActivity::class.java))
        })
//        recycler.layoutManager = LinearLayoutManager(this)
//        recycler.adapter


        supportFragmentManager.beginTransaction().replace(R.id.container, SourceListFragment())
            .commit()
    }

    fun showNewsList(source: Source) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, NewsListFragment.newInstance(source))
            .commit()
    }
}
