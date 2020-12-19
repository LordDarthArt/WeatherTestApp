package tk.lorddarthart.weathertest.app

import com.google.firebase.auth.FirebaseUser
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.di.DaggerAppComponent

class WeatherTestApp : DaggerApplication() {
    private lateinit var cicerone: Cicerone<Router>
    var firebaseUser: FirebaseUser? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        appid = instance.getString(R.string.openweathermap_api_key)

        initCicerone()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }

    companion object {
        lateinit var instance: WeatherTestApp
        lateinit var appid: String

        fun setUser(user: FirebaseUser) {
            instance.firebaseUser = user
        }
    }
}