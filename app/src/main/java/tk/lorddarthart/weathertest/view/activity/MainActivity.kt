package tk.lorddarthart.weathertest.view.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.presenter.activity.MainActivityPresenter
import tk.lorddarthart.presenter.activity.MainActivityView
import tk.lorddarthart.utils.constant.SharedPreferencesKeys.AUTHORIZED_USER
import tk.lorddarthart.utils.helper.IOnBackPressed
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.view.Screens
import tk.lorddarthart.weathertest.view.base.activity.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {

    @Inject lateinit var navigationHolder: NavigatorHolder
    @Inject lateinit var router: Router
    @Inject lateinit var firebaseAuth: FirebaseAuth

    @Inject
    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter

    @ProvidePresenter
    fun provideMainActivityPresenter(): MainActivityPresenter = mainActivityPresenter

    private var sharedPreferences: SharedPreferences? = null
    private val navigator: Navigator by lazy { SupportAppNavigator(this, R.id.main_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun start() {
        mainActivityPresenter.initMainFragment()
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)

        start()
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.main_fragment_container)

        when {
            fragment is IOnBackPressed -> fragment.onBackPressed()
            supportFragmentManager.backStackEntryCount > 0 -> supportFragmentManager.popBackStack()
            else -> mainActivityPresenter.showExitDialog()
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

    override fun initVariables() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun checkAuthorization() {
        if (firebaseAuth.currentUser != null || (sharedPreferences != null && sharedPreferences!!.contains(AUTHORIZED_USER))) {
            router.replaceScreen(Screens.CitiesListScreen)
        } else {
            router.replaceScreen(Screens.CitiesListScreen)
        }
    }
}