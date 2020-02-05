package edvinasnew.app.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edvinasnew.app.main.MainActivity
import edvinasnew.app.main.MainViewModel
import edvinasnew.app.main.MainViewModelFactory

class SplashActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(application)
        )
            .get(MainViewModel::class.java)

        startActivity(MainActivity.createIntent(this))
        finish()

//        viewModel.showTutorial.observe(this, Observer { shouldShowTutorial ->
//
//        })

    }
}
