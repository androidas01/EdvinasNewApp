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

    fun showNext(pagePosition: Int) {
        if (pagePosition > LAST_PAGE_INDEX) {
            finish()
            return
        } else {
            viewPager.currentItem = pagePosition
        }
    }

    companion object{
        private const val LAST_PAGE_INDEX = 2
    }
}
