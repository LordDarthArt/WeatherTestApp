package tk.lorddarthart.presenter.fragment.cities_list.extended

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@StateStrategyType(SkipStrategy::class)
interface ExtendedInfoFragmentView: MvpView {
}