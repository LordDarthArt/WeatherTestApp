package tk.lorddarthart.weathertest.app.presenter.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.view.activity.MainActivityView

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {
    var router: Router = App.instance.getRouter()

    fun initMainFragment() {
        router.replaceScreen(Screens.MainScreen)
    }
}