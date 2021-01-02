package tk.lorddarthart.utils.helper.preferences

import android.content.Context

interface AppPreferences {
    var context: Context

    fun init(context: Context)
    fun checkOnStart()
    fun getCitiesList(cities: MutableList<String>): List<String>
}