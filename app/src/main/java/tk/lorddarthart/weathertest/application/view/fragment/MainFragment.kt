package tk.lorddarthart.weathertest.application.view.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.design.longSnackbar
import org.json.JSONException
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.model.forecast.current.CurrentForecast
import tk.lorddarthart.weathertest.application.view.base.BaseFragment
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import tk.lorddarthart.weathertest.util.adapter.RecyclerViewAdapter
import tk.lorddarthart.weathertest.util.localdb.DatabaseHelper
import tk.lorddarthart.weathertest.util.network.HttpServiceHelper
import java.io.IOException
import java.text.ParseException
import java.util.*

class MainFragment : BaseFragment() {
    private lateinit var mSqLiteDatabase: SQLiteDatabase
    private var weather = ArrayList<CurrentForecast>()
    private var cursor: Cursor? = null
    private var dialog: ProgressDialog? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var ed: SharedPreferences.Editor
    private var opening = 0
    private var opening2 = 0
    private lateinit var cities: Array<String>
    private lateinit var consLayText: ConstraintLayout
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var editText: EditText
    private lateinit var consLayOpen: Animation
    private lateinit var consLayClose: Animation
    private lateinit var rotateForward: Animation
    private lateinit var rotateBackward: Animation
    private lateinit var tvOpen: Animation
    private lateinit var tvClose: Animation
    private var isOpen = false
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_main, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            constraintLayout = findViewById(R.id.fragment_main_layout_darken)
            consLayText = findViewById(R.id.fragment_main_floating_text_box)
            editText = findViewById(R.id.fragment_main_floating_edit_text)
            fab = findViewById(R.id.fragment_main_floating_action_button)
        }
    }

    override fun setContent() {
        super.setContent()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity)
        ed = sharedPreferences.edit()
        constraintLayout.visibility = View.VISIBLE
        mSqLiteDatabase = DatabaseHelper(
                mainActivity,
                DatabaseHelper.DATABASE_NAME,
                null,
                DatabaseHelper.DATABASE_VERSION
        ).writableDatabase
        query = ("SELECT " + DatabaseHelper.WEATHER_FILTERNAME + ", "
                + DatabaseHelper.WEATHER_DATE + ", " + DatabaseHelper.WEATHER_CITY + ", "
                + DatabaseHelper.WEATHER_NOW + ", " + DatabaseHelper.WEATHER_HIGH + " , "
                + DatabaseHelper.WEATHER_LOW + ", " + DatabaseHelper.WEATHER_TEXT + ", "
                + DatabaseHelper.WEATHER_DESCRIPTION + ", " + DatabaseHelper.WEATHER_HUMIDITY + ", "
                + DatabaseHelper.WEATHER_PRESSURE + ", " + DatabaseHelper.WEATHER_RISING + ", "
                + DatabaseHelper.WEATHER_VISIBILITY + ", " + DatabaseHelper.WEATHER_SUNRISE + ", "
                + DatabaseHelper.WEATHER_SUNSET + ", " + DatabaseHelper.WEATHER_D1 + ", "
                + DatabaseHelper.WEATHER_D2 + ", " + DatabaseHelper.WEATHER_D3 + ", "
                + DatabaseHelper.WEATHER_D4 + ", " + DatabaseHelper.WEATHER_D5 + ", "
                + DatabaseHelper.WEATHER_D6 + ", " + DatabaseHelper.WEATHER_D7 + " FROM "
                + DatabaseHelper.DATABASE_WEATHER)
        cursor = mSqLiteDatabase.rawQuery(query, arrayOfNulls(0))
        mRecyclerView = mainView.findViewById(R.id.fragment_main_recycler_view)
        layoutManager = LinearLayoutManager(mainActivity)
        mRecyclerView.layoutManager = layoutManager
        fab.setOnClickListener { animateFab() }
        rotateForward = AnimationUtils.loadAnimation(mainActivity, R.anim.rotate_forward)
        rotateBackward = AnimationUtils.loadAnimation(mainActivity, R.anim.rotate_backward)
        consLayOpen = AnimationUtils.loadAnimation(mainActivity, R.anim.conslay_open)
        consLayClose = AnimationUtils.loadAnimation(mainActivity, R.anim.conslay_close)
        tvOpen = AnimationUtils.loadAnimation(mainActivity, R.anim.tv_open)
        tvClose = AnimationUtils.loadAnimation(mainActivity, R.anim.tv_close)
        if (!sharedPreferences.contains("cities")) {
            ed.putString("cities", "Saint-Petersburg,Russia,,Moscow,Russia,,")
            ed.apply()
        }
        cities = sharedPreferences.getString("cities", "")!!.split(",,".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (editText.text.isNotEmpty() && fab.rotation != -45f) {
                    fab.setImageResource(android.R.drawable.ic_menu_send)
                    fab.rotation = -45f
                    fab.setOnClickListener {
                        fab.rotation = 0f
                        fab.setImageResource(R.drawable.ic_baseline_plus_24px)
                        animateFab()
                        fab.setOnClickListener { animateFab() }
                        ed.putString("cities", sharedPreferences.getString("cities", "") + editText.text.toString() + ",,")
                        ed.commit()
                        editText.setText("")
                        hideSoftKeyboard()
                        cities = sharedPreferences.getString("cities", "")!!.split(",,".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                        opening = 0
                        opening2 = 0
                        mRecyclerView.visibility = View.INVISIBLE
                        try {
                            for (i in cities.indices) {
//                                UpdateDatabaseTask().execute(cities[i])
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } else if (editText.text.isEmpty()) {
                    fab.setImageResource(R.drawable.ic_baseline_plus_24px)
                    fab.rotation = 0f
                    fab.setOnClickListener { animateFab() }
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
        try {
            for (i in cities.indices) {
//                UpdateDatabaseTask().execute(cities[i])
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun animateFab() {
        if (isOpen) {
            fab.startAnimation(rotateForward)
            consLayText.startAnimation(tvClose)
            consLayText.isClickable = false
            consLayText.visibility = View.GONE
            constraintLayout.startAnimation(consLayClose)
            constraintLayout.isClickable = false
            constraintLayout.visibility = View.GONE
            isOpen = false
        } else {
            fab.startAnimation(rotateBackward)
            consLayText.startAnimation(tvOpen)
            consLayText.isClickable = true
            consLayText.visibility = View.VISIBLE
            constraintLayout.isClickable = true
//            constraintLayout.setColorFilter(Color.argb(150, 155, 155, 155), PorterDuff.Mode.DARKEN)
            constraintLayout.startAnimation(consLayOpen)
            isOpen = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val container = LinearLayout(mainActivity)
        container.orientation = LinearLayout.VERTICAL
        for (i in cities.indices) {
            val holder = layoutInflater.inflate(R.layout.settings_city, null, false)
            val textViewCity = holder.findViewById<View>(R.id.tvCity) as TextView
            textViewCity.text = cities[i]
            val img = holder.findViewById<ImageView>(R.id.ivDelCity)
            img.setOnClickListener {
                try {
                    val cities = sharedPreferences.getString("cities", "")
                    val fixed = cities!!.replace(textViewCity.text.toString() + ",,", "")
                    ed.putString("cities", fixed)
                    ed.commit()
                    val query = "DELETE from " + DatabaseHelper.DATABASE_WEATHER + " WHERE " + DatabaseHelper.WEATHER_FILTERNAME + " = \"" + textViewCity.text.toString() + "\""
                    mSqLiteDatabase.execSQL(query)
                    textViewCity.visibility = View.GONE
                    img.visibility = View.GONE
                } catch (e: Exception) {
                    e.message?.let {
                        mainView.longSnackbar(it).show()
                    }
                }
            }
            container.addView(holder)
        }
        val builder = AlertDialog.Builder(mainActivity)
                .setTitle("Введите необходимые данные")
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false)
                .setView(container)
                .create()
        when (item.itemId) {
            R.id.action_setcity -> {
                builder.setOnShowListener {
                    val button = (builder as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    button.setOnClickListener {
                        try {
                            builder.dismiss()
                            mainActivity.recreate()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                builder.setOnDismissListener { }
            }
        }
        builder.show()
        return super.onOptionsItemSelected(item)
    }

    fun getTodayEvents() {
        cursor!!.moveToFirst()
        cursor!!.moveToPrevious()
        weather.clear()
        while (cursor!!.moveToNext()) {
            val weathers = CurrentForecast()
//            weathers.weather_city = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_CITY))
//            weathers.weather_now = cursor!!.getDouble(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_NOW))
//            weathers.weather_date = cursor!!.getLong(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_DATE))
//            weathers.weather_high = cursor!!.getDouble(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_HIGH))
//            weathers.weather_low = cursor!!.getDouble(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_LOW))
//            weathers.weather_sunrise = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_SUNRISE))
//            weathers.weather_sunset = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_SUNSET))
//            weathers.weather_text = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_TEXT))
//            weathers.weather_description = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_DESCRIPTION))
//            weathers.weather_humidity = cursor!!.getDouble(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_HUMIDITY))
//            weathers.weather_pressure = cursor!!.getDouble(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_PRESSURE))
//            weathers.weather_rising = cursor!!.getLong(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_RISING))
//            weathers.weather_visibility = cursor!!.getDouble(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_VISIBILITY))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D1))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D2))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D3))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D4))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D5))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D6))
//            weathers.weather_d1 = cursor!!.getString(cursor!!.getColumnIndex(DatabaseHelper.WEATHER_D7))
            weather.add(weathers)
        }
        initializeAdapter()
    }

    private fun initializeAdapter() {
        val itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
//                val startTaskInfoActivity = Intent(mainActivity, ExtendedFragmentTodayPage::class.java)
//                val weathers = weather[position]
//                startTaskInfoActivity.putExtra("weatherCity", weathers.weather_city)
//                startTaskInfoActivity.putExtra("weatherNow", weathers.weather_now)
//                val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
//                val date = Date(weathers.weather_date)
//                val dateText = sdf.format(date)
//                startTaskInfoActivity.putExtra("weatherDate", dateText)
//                startTaskInfoActivity.putExtra("weatherHigh", weathers.weather_high)
//                startTaskInfoActivity.putExtra("weatherLow", weathers.weather_low)
//                startTaskInfoActivity.putExtra("weatherSunrise", weathers.weather_sunrise)
//                startTaskInfoActivity.putExtra("weatherSunset", weathers.weather_sunset)
//                startTaskInfoActivity.putExtra("weatherDescription", weathers.weather_description)
//                startTaskInfoActivity.putExtra("weatherText", weathers.weather_text)
//                startTaskInfoActivity.putExtra("weatherHumidity", weathers.weather_humidity)
//                startTaskInfoActivity.putExtra("weatherPressure", weathers.weather_pressure)
//                startTaskInfoActivity.putExtra("weatherRising", weathers.weather_rising)
//                startTaskInfoActivity.putExtra("weatherVisibility", weathers.weather_visibility)
//                startTaskInfoActivity.putExtra("weatherD1", weathers.weather_d1)
//                startTaskInfoActivity.putExtra("weatherD2", weathers.weather_d2)
//                startTaskInfoActivity.putExtra("weatherD3", weathers.weather_d3)
//                startTaskInfoActivity.putExtra("weatherD4", weathers.weather_d4)
//                startTaskInfoActivity.putExtra("weatherD5", weathers.weather_d5)
//                startTaskInfoActivity.putExtra("weatherD6", weathers.weather_d6)
//                startTaskInfoActivity.putExtra("weatherD7", weathers.weather_d7)
//                startActivity(startTaskInfoActivity)
            }

            override fun onButtonCvMenuClick(view: View, position: Int) {

            }
        }
        val recyclerViewAdapter = RecyclerViewAdapter(mainActivity, weather, itemTouchListener)
        mRecyclerView.adapter = recyclerViewAdapter
    }

    fun tableExists(db: SQLiteDatabase?, tableName: String?): Boolean {
        if (tableName == null || db == null || !db.isOpen) {
            return false
        }
        val cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", arrayOf("table", tableName))
        if (!cursor.moveToFirst()) {
            cursor.close()
            return false
        }
        val count = cursor.getInt(0)
        cursor.close()
        return count > 0
    }

    private inner class UpdateDatabaseTask : AsyncTask<String, Void, Void>() {
        var responseCode: Int = 0

        override fun onPreExecute() {
            if (opening == 0) {
                dialog = ProgressDialog(mainActivity)
                dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                dialog!!.setMessage("Синхронизация…")
                dialog!!.setCancelable(false)
                dialog!!.show()
                opening++
            }
        }

        override fun doInBackground(vararg strings: String): Void? {
            try {
                responseCode = HttpServiceHelper.getTasks(strings[0])
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: Void) {
            opening2++
            if (opening2 == cities.size) {
                cursor = mSqLiteDatabase.rawQuery(query, arrayOfNulls(0))
                getTodayEvents()
                dialog!!.dismiss()
                mRecyclerView.visibility = View.VISIBLE
                constraintLayout.visibility = View.GONE
            }
        }
    }

    fun showSoftKeyboard() {
        try {
            val inputMethodManager = mainActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(mainView.windowToken, InputMethodManager.SHOW_FORCED, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun hideSoftKeyboard() {
        try {
            val inputMethodManager = mainActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(mainView.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
