package tk.lorddarthart.weathertest.application.presenter.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.App
import tk.lorddarthart.weathertest.application.Screens
import tk.lorddarthart.weathertest.application.view.activity.MainActivity
import tk.lorddarthart.weathertest.application.view.fragment.main.MainFragment

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivity>() {
    private var router: Router = App.instance.getRouter()

    fun initMainFragment() {
        router.navigateTo(Screens.MainScreen)
    }
}