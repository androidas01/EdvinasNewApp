package edvinasnew.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import edvinasnew.app.tutorial.TutorialActivity
import edvinasnew.app.tutorial.TutorialScreenConfig
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //startActivity(Intent(this, TutorialActivity::class.java))
        //startActivity(Intent(this, TutorialActivity ::class.java))

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
