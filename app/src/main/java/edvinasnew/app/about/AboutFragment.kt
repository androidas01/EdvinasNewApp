package edvinasnew.app.about

//import jdk.nashorn.internal.objects.NativeDate.getTime

//import javax.swing.UIManager.put

//import jdk.nashorn.internal.objects.NativeDate.getTime

import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tomasNewsApp.utils.location.LocationManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture
import com.github.mikephil.charting.utils.MPPointD
import com.google.android.gms.location.LocationServices
import edvinasnew.app.R
import edvinasnew.app.main.MainActivity
import edvinasnew.app.utils.formatDate
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_news.toolbar
import kotlinx.android.synthetic.main.fragment_tutorial.view.*
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue


class AboutFragment : Fragment() {

    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_about, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (requireActivity() as MainActivity).title = "About"
        //(requireActivity() as MainActivity).version.text = "Version ??.??.??"
        //(requireActivity() as MainActivity).reserved.text = "2020-... EdvinasJ "

        toolbar.setNavigationOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
        }

//        radioGroup.setOnCheckedChangeListener { group, checkedId ->
//            if (R.id.radioButton == checkedId) {
//                Toast.makeText(this.context, "Radiobutton1", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(this.context, "Radiobutton2", Toast.LENGTH_LONG).show()
//            }
//        }
//

//        var selected = "";
//
//        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                selected = selected + "check1";
//                Toast.makeText(this.context, selected, Toast.LENGTH_LONG).show()
//            } else {
//                selected = selected.replace("check1", "", true)
//                Toast.makeText(this.context, selected, Toast.LENGTH_LONG).show()
//            }
//        }
//
//        checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                selected = selected + " check2";
//                Toast.makeText(this.context, selected, Toast.LENGTH_LONG).show()
//            } else {
//                selected = selected.replace(" check2", "", true)
//                Toast.makeText(this.context, selected, Toast.LENGTH_LONG).show()
//            }
//        }
//
//        textView.setFocusable(false)
//
//        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                calendarView.isInvisible = false;
//                Toast.makeText(this.context, "switch", Toast.LENGTH_LONG).show()
//            }
//            else{
//                calendarView.isInvisible = true;
//                Toast.makeText(this.context, "switch off", Toast.LENGTH_LONG).show()
//        }
//    }
//        calendarView.isInvisible = true;
//
//        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
//
//            textView.setText(year.toString() + "-" + (
//                    if(month.toString().length == 1){
//                        ("0" + (month + 1))
//                    }else{
//                        month + 1
//                    }
//                    ).toString() + "-" + (
//                    if (dayOfMonth.toString().length == 1)
//                    {
//                        ("0" + dayOfMonth)
//                    } else{
//                        dayOfMonth
//                    }
//                    ).toString())
//
//            Toast.makeText(
//                this.context,
//                textView.text,
//                Toast.LENGTH_LONG
//            ).show()
//
//
//
//            calendarView.isVisible = false;
//            switch1.isChecked = false;
//        }

//        val firstChartEntity = ChartEntity(Color.WHITE, graph1)
//        //val secondChartEntity = ChartEntity(Color.YELLOW, graph2)
//
//        val list = ArrayList<ChartEntity>()
//        list.add(firstChartEntity)
//        //list.add(secondChartEntity)
//        lineChart.legendArray = legendArr
//        lineChart.setList(list)
//        entries.add(
//            Entry(
//                data.getDate().getTime().toFloat(),
//                data.getValueY().toFloat()
//            )
//        )

        val currentDate: String =
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

        val listData2 = ArrayList<Entry>()
        listData2.add(Entry(0f, 10f))
        listData2.add(Entry(1f, 22f))
        listData2.add(Entry(2f, 30f))
        listData2.add(Entry(3f, 100f))
        listData2.add(Entry(4f, 40f))
        listData2.add(Entry(5f, 20f))

        val lineDataSet2 = LineDataSet(listData2, getString(R.string.newest))
        lineDataSet2.color = ContextCompat.getColor(this.context!!, R.color.colorPrimaryDark)
        lineDataSet2.valueTextColor = ContextCompat.getColor(this.context!!, android.R.color.holo_blue_light)


        val listData = ArrayList<Entry>()
        listData.add(Entry(0f, 5f))
        listData.add(Entry(1f, 10f))
        listData.add(Entry(2f, 30f))
        listData.add(Entry(3f, 30f))
        listData.add(Entry(4f, 20f))
        listData.add(Entry(5f, 11f))

        val lineDataSet = LineDataSet(listData, getString(R.string.Grafikas))
        lineDataSet.color = ContextCompat.getColor(this.context!!, R.color.colorAccent)
        lineDataSet.valueTextColor = ContextCompat.getColor(this.context!!, R.color.colorAccent)

        val lineData = LineData(lineDataSet, lineDataSet2)
        lineChart?.data = lineData



        lineChart.getDescription().setEnabled(false)

        lineChart.xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)

        val rightYAxis: YAxis = lineChart.getAxisRight()
        rightYAxis.isEnabled = false


        val xAxis: XAxis = lineChart.getXAxis()
        xAxis.labelRotationAngle = -50f

        //val xAxisLabels = listOf("1", "2", "3", "4", "5", "6")
        //lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

//Set label count to 5 as we are displaying 5 star rating
        lineChart.xAxis.setLabelCount(5)

//Now add the labels to be added on the vertical axis
        val values = arrayOf(
            formatDate(Calendar.getInstance().time),
            "2",
            "3",
            "4",
            "5",
            "6"
        )

        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(values)

        lineChart.setTouchEnabled(true)

        // remove backgroud lines
        //lineChart.getXAxis().setDrawGridLines(false);
        //lineChart.getAxisLeft().setDrawGridLines(false);
        //lineChart.getAxisRight().setDrawGridLines(false);

        //  align lagend position
        lineChart.legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        lineChart.legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        lineChart.legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        lineChart.legend.setDrawInside(false);

        lineChart?.invalidate() //refresh

        lineChart.setOnClickListener{click ->
            //Toast.makeText(this.context, "", Toast.LENGTH_LONG).show()



            //lineDataSet.color = ContextCompat.getColor(this.context!!, R.color.White)
            //lineDataSet.valueTextColor = ContextCompat.getColor(this.context!!, R.color.White)
            //lineDataSet.circleHoleColor = ContextCompat.getColor(this.context!!, R.color.White)

            //lineDataSet.isVisible = false;



            //listData.clear()
            //val lineDataSet = LineDataSet(listData, getString(R.string.Grafikas))

            lineDataSet.isVisible = false
            //val lineData = LineData(lineDataSet, lineDataSet2)
            //lineChart?.data = lineData
            //lineChart?.invalidate()

        }


        button.setOnClickListener{ click ->
            Toast.makeText(this.context, "asd", Toast.LENGTH_LONG).show()

            lineDataSet.isVisible = false
            if (lineDataSet.isVisible == false){
                lineDataSet.isVisible = true
            }
            else{
                lineDataSet.isVisible = false
            }

        }




        // location
        val manager =
            LocationManager(
                LocationServices.getFusedLocationProviderClient(requireActivity()),
                LocationServices.getSettingsClient(requireActivity())
            )
        disposable = manager.locationUpdates
            .mergeWith(manager.lastLocation)
            .doOnSubscribe { manager.checkLocationSettings(requireActivity()) }
            .subscribe {
                Log.d("location", it.toString())
            }
    }


//    private val graph1 = floatArrayOf(113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f)
//    //private val graph2 = floatArrayOf(0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f, 20000f, 12000f, 124400f, 160000f)
//    private val legendArr = arrayOf("05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31")

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }


    companion object {
        fun newInstance(): AboutFragment {
            val arguments = Bundle()
            val fragment = AboutFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}


