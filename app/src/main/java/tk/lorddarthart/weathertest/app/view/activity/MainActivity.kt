package tk.lorddarthart.weathertest.app.view.activity

import android.os.Bundle
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.WeatherTestApp
import tk.lorddarthart.weathertest.app.presenter.activity.MainActivityPresenter
import tk.lorddarthart.weathertest.app.view.base.activity.BaseActivity
import tk.lorddarthart.weathertest.util.helper.IOnBackPressed
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {

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
            fragment is IOnBackPressed -> { fragment.onBackPressed() }
            supportFragmentManager.backStackEntryCount > 0  -> { supportFragmentManager.popBackStack() }
            else -> { mainActivityPresenter.showExitDialog() }
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
}