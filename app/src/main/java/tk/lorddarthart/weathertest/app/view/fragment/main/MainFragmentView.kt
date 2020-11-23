package tk.lorddarthart.weathertest.app.view.fragment.main

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import tk.lorddarthart.weathertest.databinding.FragmentMainBinding

/** Created by LordDarthArt at 19.10.2019 */
@StateStrategyType(SkipStrategy::class)
interface MainFragmentView: MvpView {
}