package tk.lorddarthart.weathertest

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.di.DaggerAppComponent

class WeatherTestApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}