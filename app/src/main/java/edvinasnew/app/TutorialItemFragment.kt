package edvinasnew.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tutorial.*

class TutorialItemFragment : Fragment() {

    lateinit var tutorialText: String
    var page: Int = 0;
    var tutorialImage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = arguments!!.getParcelable<TutorialScreenConfig> (KEY_CONFIG)!!

        tutorialText =  config.tutorialText //arguments!!.getString(KEY_LABEL) ?: ""
        page =  config.page //arguments!!.getInt(KEY_PAGE)
        tutorialImage = config.tutorialImage //arguments!!.getInt(KEY_IMAGE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "$tutorialText $page"
        imageView.setImageResource(tutorialImage)
        next.setBackgroundColor(R.drawable.ic_launcher_background)
        view.findViewById<Button>(R.id.next).setOnClickListener {
            //requireActivity().finish() //Pabaigia

            (requireActivity() as TutorialActivity).showNext(page) //Kitas fragmentas
        }
    }

    companion object {
//        private const val KEY_LABEL = "tutorial_text"
//        private const val KEY_PAGE = "page"
//        private const val KEY_IMAGE = "tutorialImage"
          private const val KEY_CONFIG = "key_config"

        fun newInstance(config: TutorialScreenConfig): TutorialItemFragment {
            val arguments = Bundle()
//            arguments.putString(KEY_LABEL, tutorialText)
//            arguments.putInt(KEY_PAGE, page)
//            arguments.putInt(KEY_IMAGE, tutorialImage)

            arguments.putParcelable(KEY_CONFIG, config)

            val fragment = TutorialItemFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}