package tk.lorddarthart.weathertest.app.view.fragment.auth

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface AuthFragmentView : MvpView {

    fun showErrorToUser(text: String)

    fun setError(errorText: String)

}