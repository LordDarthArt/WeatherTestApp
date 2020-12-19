package tk.lorddarthart.weathertest.app.presenter.activity

import android.content.SharedPreferences
import androidx.preference.PreferenceManager


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.app.WeatherTestApp
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.view.activity.MainActivityView
import tk.lorddarthart.weathertest.util.constant.SharedPreferencesKeys.AUTHORIZED_USER

/** Created by LordDarthArt at 23.09.2019. */
@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {
    var router: Router = WeatherTestApp.instance.getRouter()

    private var sharedPreferences: SharedPreferences? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null

    fun initMainFragment() {
        initVariables()
        checkAuthorization()
    }

    private fun initVariables() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth?.currentUser
        firebaseUser?.let { WeatherTestApp.setUser(it) }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WeatherTestApp.instance)
    }

    fun checkAuthorization() {
        if (WeatherTestApp.instance.firebaseUser != null || (sharedPreferences != null && sharedPreferences!!.contains(AUTHORIZED_USER))) {
            router.replaceScreen(Screens.CitiesListScreen)
        } else {
            router.replaceScreen(Screens.CitiesListScreen)
        }
    }

    fun showExitDialog() {
        viewState.showExitDialog()
    }
}