package tk.lorddarthart.weathertest.util.helper

import android.content.Context

interface AppPreferences {
    fun init(context: Context)
    fun checkOnStart()
    fun getCitiesList(cities: MutableList<String>): List<String>
}