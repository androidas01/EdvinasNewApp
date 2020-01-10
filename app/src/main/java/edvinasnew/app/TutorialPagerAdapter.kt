package edvinasnew.app

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class TutorialPagerAdapter(fragmentManager: FragmentManager, private val resources: Resources) : FragmentPagerAdapter(fragmentManager) {
    val Images = listOf(R.drawable.google, R.drawable.asdd, R.drawable.new2, R.drawable.matrix)

    override fun getItem(position: Int): Fragment {
        val config = TutorialScreenConfig(
            tutorialText = resources.getString(R.string.Puslapis),
            page = position,
            tutorialImage = Images[position]
        )
        return TutorialItemFragment.newInstance(config)
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    companion object{
        private const val PAGE_COUNT = 3
    }
}