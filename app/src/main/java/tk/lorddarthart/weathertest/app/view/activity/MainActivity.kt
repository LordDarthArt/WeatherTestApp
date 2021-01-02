package tk.lorddarthart.weathertest.app.view.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.presenter.activity.MainActivityPresenter
import tk.lorddarthart.presenter.activity.MainActivityView
import tk.lorddarthart.utils.constant.SharedPreferencesKeys.AUTHORIZED_USER
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.WeatherTestApp
import tk.lorddarthart.weathertest.app.view.base.activity.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {
    val router: Router by lazy { WeatherTestApp.instance.getRouter() }

    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null
    private var sharedPreferences: SharedPreferences? = null

    @Inject
    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter

    @ProvidePresenter
    fun provideMainActivityPresenter(): MainActivityPresenter = mainActivityPresenter

    var navigator: Navigator = SupportAppNavigator(this, R.id.main_fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()
    }

    override fun start() {
        mainActivityPresenter.initMainFragment()
    }

    override fun onResume() {
        super.onResume()
        WeatherTestApp.instance.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        WeatherTestApp.instance.getNavigatorHolder().removeNavigator()
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.main_fragment_container)

        when {
            fragment is tk.lorddarthart.utils.helper.IOnBackPressed -> {
                fragment.onBackPressed()
            }
            supportFragmentManager.backStackEntryCount > 0 -> {
                supportFragmentManager.popBackStack()
            }
            else -> {
                mainActivityPresenter.showExitDialog()
            }
        }
    }

    override fun showExitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.exit)
            .setMessage(R.string.are_you_sure)
            .setPositiveButton(R.string.yes) { _, _ -> this.finishAffinity() }
            .setNeutralButton(R.string.no) { dialog, _ -> dialog.cancel() }
            .show()
    }

    override fun checkAuthorization() {
        if (WeatherTestApp.instance.firebaseUser != null || (sharedPreferences != null && sharedPreferences!!.contains(AUTHORIZED_USER))) {
            router.replaceScreen(Screens.CitiesListScreen)
        } else {
            router.replaceScreen(Screens.CitiesListScreen)
        }
    }

    override fun initVariables() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth?.currentUser
        firebaseUser?.let { WeatherTestApp.setUser(it) }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WeatherTestApp.instance)
    }
}