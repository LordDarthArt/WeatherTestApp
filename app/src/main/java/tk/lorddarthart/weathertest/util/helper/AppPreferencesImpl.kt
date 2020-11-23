package tk.lorddarthart.weathertest.util.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.App

class AppPreferencesImpl(private val gson: Gson): AppPreferences {
    private val sharedPreferences: SharedPreferences? = null
    private val editor: SharedPreferences.Editor by lazy {
        sharedPreferences!!.edit()
    }

    override fun init(context: Context) {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun checkOnStart() {
        if (sharedPreferences?.contains("city#0") == false) {
            editor.putString("city#0", "Saint-Petersburg,Russia")
            editor.putString("city#1", "Moscow,Russia")
            editor.apply()
        }
    }

    override fun getCitiesList(cities: MutableList<String>): List<String> {
        cities.clear()
        for (i in 0 until -1) {
            if (sharedPreferences?.contains("city#$i") == false) {
                break
            } else {
                sharedPreferences?.getString("city#$i", App.instance.getString(R.string.error))?.let {
                    cities.add(it)
                }
            }
        }
        return cities
    }
}