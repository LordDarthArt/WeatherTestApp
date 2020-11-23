package tk.lorddarthart.weathertest.app.view.activity

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface MainActivityView: MvpView {
    fun start()

    fun showExitDialog()
}