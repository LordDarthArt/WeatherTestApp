package tk.lorddarthart.weathertest.app.view.fragment.main.extended

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import tk.lorddarthart.weathertest.databinding.FragmentExtendedBinding

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@StateStrategyType(SkipStrategy::class)
interface ExtendedInfoFragmentView: MvpView {
}