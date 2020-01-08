package edvinasnew.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.random.Random

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                TutorialItemFragment.newInstance(getString(R.string.Puslapis), 0, R.drawable.new2)
            )
            .commit()
    }

    fun showNext(currentPage: Int) {
        if (currentPage == 2) {
            finish()
            return
        }

        var Images = arrayOf(R.drawable.google, R.drawable.asdd, R.drawable.new2, R.drawable.matrix)
        var n = Random.nextInt(3)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                TutorialItemFragment.newInstance(
                    getString(R.string.Puslapis),
                    currentPage + 1,
                    Images[n] //R.drawable.google
                )
            )
            .commit()

//        when (currentPage) {
//            0 -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(
//                        R.id.container,
//                        TutorialItemFragment.newInstance(
//                            "Puslapis",
//                            currentPage + 1,
//                            R.drawable.google
//                        )
//                    )
//                    .commit()
//            }
//            1 -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(
//                        R.id.container,
//                        TutorialItemFragment.newInstance(
//                            "Puslapis",
//                            currentPage + 1,
//                             R.drawable.new2
//                        )
//                    )
//                    .commit()
//            }
//        }
    }
}
