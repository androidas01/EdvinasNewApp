package edvinasnew.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_source.*

class SourceListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val adapter = SourceListItemeAdapter(object : OnSourceSelectedListener {
            override fun onSourceSelected(source: Source) {
                this@SourceListFragment.onSourceSelected(source)
            }
        })
        adapter.setItems(news)
        recycler.adapter = adapter
    }

    fun onSourceSelected(source: Source) {
        (requireActivity() as MainActivity).showNewsList(source)
    }

    var news = listOf(
        Source(
            "New car",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "Fire",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "Test",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "title",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "title",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "title",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "title",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        ), Source(
            "title",
            "Regis, tuo viskas turėjo ir pasibaigti – į iraniečių raketinį smūgį JAV nesureagavo atsakomosiomis karinėmis priemonėmis, raketos nepridarė didelių nuostolių, o abi pusės – tiek JAV, tiek Teheranas, nepaisant karingos retorikos, leido suprasti, kad eskalacijos"
        )
    )
}

interface OnSourceSelectedListener {
    fun onSourceSelected(source: Source)
}