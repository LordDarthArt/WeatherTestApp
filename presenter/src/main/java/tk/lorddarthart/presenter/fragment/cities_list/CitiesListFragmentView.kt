package tk.lorddarthart.presenter.fragment.cities_list

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import tk.lorddarthart.data.entities.ForecastEntity

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@StateStrategyType(SkipStrategy::class)
interface CitiesListFragmentView: MvpView {

    /**
     * Created by LordDarthArt at 26.10.2019
     * Shows loading dialog on network sync start
     */
    fun showLoading()

    /**
     * Created by LordDarthArt at 26.10.2019
     * Hides loading dialog after we don't need it anymore
     */
    fun hideLoading()

    /**
     * Created by LordDarthArt at 26.10.2019
     * Swaps normal and darken layouts (making darken layout (in)visible)
     * @param [darken] - Boolean that provides info on darken layout visibility
     */
    fun swapDarkenAndRecycler(darken: Boolean)

    /**
     * Created by LordDarthArt at 26.10.2019
     * Animating opening of edittext's field
     */
    fun animateFloatingActionButtonsAction()

    /**
     * Created by LordDarthArt at 26.10.2019
     * This is what should be executed after network request is completed
     */
    fun onPostExecute()

    fun showNetworkError()

    fun displayData(forecasts: List<ForecastEntity>)

    fun openExtendedInfo()
}