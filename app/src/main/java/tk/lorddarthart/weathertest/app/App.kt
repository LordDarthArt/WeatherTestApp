package tk.lorddarthart.weathertest.app

import android.app.Application
import com.google.firebase.auth.FirebaseUser
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.R

class App : Application() {
    private lateinit var cicerone: Cicerone<Router>
    var firebaseUser: FirebaseUser? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        appid = instance.getString(R.string.openweathermap_api_key)

        initCicerone()
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
        lateinit var instance: App
        lateinit var appid: String

        fun setUser(user: FirebaseUser) {
            instance.firebaseUser = user
        }
    }
}