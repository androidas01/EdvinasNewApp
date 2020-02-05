package edvinasnew.app.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
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

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            onBottomNavigationEvent(it)
        }


        // location permissions granting
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val lastKnownLocation =
                getSystemService<LocationManager>()?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            Log.e("location", lastKnownLocation.toString())
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 23)
        }

        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(application)
        )
            .get(MainViewModel::class.java)

        // viewModel.showTutorial.observe(this, Observer { newData ->
        // this.showTutorial()
        // })

        if (savedInstanceState == null) {
            this.showSource()
        }
        // this.showAbout()

        // setSupportActionBar(toolbar)
        // actionBar?.setDisplayShowHomeEnabled(true)
        // title = "Source List"
    }

//    private fun showTutorial() {
//        startActivity(Intent(this, TutorialActivity::class.java))
//    }


    // location permissions granting and long, lat taking
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val lastKnownLocation =
                    getSystemService<LocationManager>()?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                Log.e("location", lastKnownLocation.toString())
                Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show()
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    //oh come on
                    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 23)
                } else {
                    //explain how to get toi settings
                    Toast.makeText(this, "always denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    // finish permissions

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

    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}