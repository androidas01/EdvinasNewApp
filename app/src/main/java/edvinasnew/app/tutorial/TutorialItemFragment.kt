package edvinasnew.app.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import edvinasnew.app.R
import kotlinx.android.synthetic.main.fragment_tutorial.*

class TutorialItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val config = arguments!!.getParcelable<TutorialScreenConfig> (
            KEY_CONFIG
        )!!
        textView.text = "${config.tutorialText}"
        imageView.setImageResource(config.tutorialImage)
        next.setBackgroundColor(R.drawable.ic_launcher_background)
        view.findViewById<Button>(R.id.next).setOnClickListener {
            (requireActivity() as TutorialActivity).showNext()
        }
    }

    companion object {
        private const val KEY_CONFIG = "key_config"

        fun newInstance(config: TutorialScreenConfig): TutorialItemFragment {
            val arguments = Bundle()
            arguments.putParcelable(KEY_CONFIG, config)
            val fragment = TutorialItemFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}