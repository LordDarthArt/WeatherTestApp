package tk.lorddarthart.weathertest.application

import com.arellomobile.mvp.MvpApplication
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.R

class App : MvpApplication() {
    val TAG = this::class.java.simpleName
    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        instance = this

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
        val appid = instance.getString(R.string.openweathermap_api_key)
    }
}