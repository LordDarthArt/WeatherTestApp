package tk.lorddarthart.weathertest.application.view.fragment.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by LordDarthArt at 19.10.2019
 */
@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainFragmentView: MvpView {
    /**
     * Created by LordDarthArt at 19.10.2019
     * Shows loading dialog on network sync start
     */
    fun showLoadingDialog()

    /**
     * Created by LordDarthArt at 19.10.2019
     * Hides loading dialog after we don't need it anymore
     */
    fun hideLoadingDialog()

    /**
     * Created by LordDarthArt at 19.10.2019
     * Swaps normal and darken layouts (making darken layout (in)visible)
     * @param [darken] - Boolean that provides info on darken layout visibility
     */
    fun swapDarkenAndRecycler(darken: Boolean)

    /**
     * Created by LordDarthArt at 19.10.2019
     * Animating opening of edittext's field
     */
    fun animateFloatingActionButtonsAction()

    /**
     * Created by LordDarthArt at 19.10.2019
     * This is what should be executed after network request is completed
     */
    fun onPostExecute()

    /**
     * Created by LordDarthArt at 20.10.2019
     * Sometimes software keyboard not showing automatically, and we must close it programmatically.
     * This fun's target is to do such a thing.
     */
    fun showSoftKeyboard()

    /**
     * Created by LordDarthArt at 20.10.2019
     * Sometimes software keyboard not hiding automatically, and we must close it programmatically.
     * This fun's target is to do such a thing.
     */
    fun hideSoftKeyboard()
}