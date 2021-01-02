package tk.lorddarthart.utils.helper.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import tk.lorddarthart.utils.R
import kotlin.properties.Delegates

class AppPreferencesImpl(private val gson: Gson): AppPreferences {
    private val sharedPreferences: SharedPreferences by Delegates.notNull()
    private val editor: SharedPreferences.Editor by lazy { sharedPreferences.edit() }
    override lateinit var context: Context

    override fun init(context: Context) {
        this.context = context
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun checkOnStart() {
        if (!sharedPreferences.contains("city#0")) {
            editor.putString("city#0", "Saint-Petersburg,Russia")
            editor.putString("city#1", "Moscow,Russia")
            editor.apply()
        }
    }

    override fun getCitiesList(cities: MutableList<String>): List<String> {
        cities.clear()
        for (i in 0 until -1) {
            if (!sharedPreferences.contains("city#$i")) {
                break
            } else {
                sharedPreferences.getString("city#$i", context.getString(R.string.error))?.let { cities.add(it) }
            }
        }
        return cities
    }
}