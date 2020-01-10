package edvinasnew.app.tutorial

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import edvinasnew.app.R

class TutorialPagerAdapter(fragmentManager: FragmentManager, private val resources: Resources) :
    FragmentPagerAdapter(fragmentManager) {
    val configs = listOf(
        TutorialScreenConfig(
            "GooooOOOgle it",
            R.drawable.google
        ),
        TutorialScreenConfig(
            "asssdd",
            R.drawable.asdd
        ),
        TutorialScreenConfig(
            "newwwww",
            R.drawable.new2
        )
    )

    override fun getItem(position: Int): Fragment {
        return TutorialItemFragment.newInstance(configs[position])
    }

    override fun getCount(): Int {
        return configs.size
    }
}