package edvinasnew.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tutorial.*
import kotlin.random.Random

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val adapter = TutorialPagerAdapter(supportFragmentManager, resources)

        viewPager.adapter = adapter
    }

    fun showNext() {
        if (viewPager.currentItem >= viewPager.adapter!!.count - 1) {
            finish()
            return
        } else {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }
}
