package tk.lorddarthart.weathertest.util.helper

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.App

object SharedPreferencesHelper {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
    private val ed: SharedPreferences.Editor = sharedPreferences.edit()

    fun checkOnStart() {
        if (!sharedPreferences.contains("cities")) {
            ed.putString("city#0", "Saint-Petersburg,Russia")
            ed.putString("city#1", "Moscow,Russia")
            ed.apply()
        }
    }

    fun getCitiesList(cities: MutableList<String>): MutableList<String> {
        cities.clear()
        for (i in 0 until -1) {
            if (!sharedPreferences.contains("city#$i")) {
                break
            } else {
                sharedPreferences.getString("city#$i", App.instance.getString(R.string.error))?.let {
                    cities.add(it)
                }
            }
        }
        return cities
    }
}