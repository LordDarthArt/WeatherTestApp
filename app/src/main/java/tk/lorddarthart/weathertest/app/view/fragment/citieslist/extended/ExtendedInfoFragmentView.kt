package tk.lorddarthart.weathertest.app.view.fragment.citieslist.extended

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@StateStrategyType(SkipStrategy::class)
interface ExtendedInfoFragmentView: MvpView {
}