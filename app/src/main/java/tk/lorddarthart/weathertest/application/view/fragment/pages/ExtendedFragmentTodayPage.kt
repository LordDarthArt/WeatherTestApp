package tk.lorddarthart.weathertest.application.view.fragment.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tk.lorddarthart.weathertest.application.view.base.BaseFragment
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.BasePageFragment
import tk.lorddarthart.weathertest.util.constant.Format.SIMPLE_DATE_FORMAT
import java.text.SimpleDateFormat

class ExtendedFragmentTodayPage : BasePageFragment() {

    private lateinit var simpleDateFormat: SimpleDateFormat

    private lateinit var txtDay: TextView
    private lateinit var txtMonthYear: TextView
    private lateinit var txtText: TextView
    private lateinit var txtTemp: TextView
    private lateinit var txtTitle: TextView
    private lateinit var txtHumidity: TextView
    private lateinit var txtPressure: TextView
    private lateinit var txtRising: TextView
    private lateinit var txtVisibility: TextView

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.page_today, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            txtDay = findViewById(R.id.current_date_value)
            txtMonthYear = findViewById(R.id.current_month_year)
            txtText = findViewById(R.id.current_weather_description)
            txtTemp = findViewById(R.id.current_weather_temperature)
            txtTitle = findViewById(R.id.current_fragment_title)
            txtHumidity = findViewById(R.id.txt_humidity_value)
            txtPressure = findViewById(R.id.txt_pressure_value)
            txtRising = findViewById(R.id.txt_uv_index_value)
            txtVisibility = findViewById(R.id.txt_visibility_value)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun setContent() {
        super.setContent()
        simpleDateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT)
    }
}
