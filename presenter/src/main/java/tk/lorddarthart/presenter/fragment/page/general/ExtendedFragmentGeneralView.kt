package tk.lorddarthart.presenter.fragment.page.general

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by LordDarthArt on 27.10.2019.
 */
@StateStrategyType(SkipStrategy::class)
interface ExtendedFragmentGeneralView: MvpView {
}