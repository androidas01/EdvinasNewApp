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

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        tutorialText = arguments!!.getString(KEY_LABEL) ?: ""
        page = arguments!!.getInt(KEY_PAGE)
        tutorialImage = arguments!!.getInt(KEY_IMAGE)
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
        view.findViewById<Button>(R.id.next).setOnClickListener {
            //requireActivity().finish() //Pabaigia

            (requireActivity() as TutorialActivity).showNext(page) //Kitas fragmentas
        }
    }

    companion object{
        private const val KEY_LABEL = "tutorial_text"
        private const val KEY_PAGE = "page"
        private const val KEY_IMAGE = "tutorialImage"

        fun newInstance (tutorialText: String, page: Int, tutorialImage: Int) : TutorialItemFragment {
            val arguments = Bundle()
            arguments.putString(KEY_LABEL, tutorialText)
            arguments.putInt(KEY_PAGE, page)
            arguments.putInt(KEY_IMAGE, tutorialImage)

            val fragment = TutorialItemFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}