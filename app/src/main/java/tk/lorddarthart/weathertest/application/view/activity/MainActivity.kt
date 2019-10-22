package tk.lorddarthart.weathertest.application.view.activity

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.App
import tk.lorddarthart.weathertest.application.presenter.activity.MainActivityPresenter
import tk.lorddarthart.weathertest.util.moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter
    
    private var navigator: Navigator = SupportAppNavigator(this, R.id.main_fragment_container)

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
        App.instance.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.getNavigatorHolder().removeNavigator()
    }
}