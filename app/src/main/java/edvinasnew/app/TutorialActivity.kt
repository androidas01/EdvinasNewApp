package edvinasnew.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        supportFragmentManager
                .beginTransaction()
            .replace(R.id.container,  TutorialItemFragment.newInstance("Puslapis", 0, R.drawable.new2))
            .commit()
    }

    fun showNext(currentPage: Int) {
        if (currentPage == 2){
            finish()
            return
        }

        when (currentPage) {
            0 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container,  TutorialItemFragment.newInstance("Puslapis", currentPage + 1, R.drawable.google))
                    .commit()
            }
            1 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container,  TutorialItemFragment.newInstance("Puslapis", currentPage + 1, R.drawable.new2))
                    .commit()
            }
        }


    }
}
