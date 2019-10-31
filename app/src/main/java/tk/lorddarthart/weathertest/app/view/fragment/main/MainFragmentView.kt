package tk.lorddarthart.weathertest.app.view.fragment.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import tk.lorddarthart.weathertest.databinding.FragmentMainBinding

/**
 * Created by LordDarthArt at 19.10.2019
 */
@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainFragmentView: MvpView {
}