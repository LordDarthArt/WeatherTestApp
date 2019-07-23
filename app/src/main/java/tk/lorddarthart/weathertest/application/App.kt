package tk.lorddarthart.weathertest.application

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Application
        const val TAG = "App"
    }
}