package tk.lorddarthart.weathertest.app.view.fragment.main.cities_list

import com.arellomobile.mvp.MvpView
import tk.lorddarthart.weathertest.databinding.FragmentCitiesListBinding

/**
 * Created by LordDarthArt on 26.10.2019.
 */
interface CitiesListFragmentView: MvpView {

    /**
     * Created by LordDarthArt at 26.10.2019
     * Shows loading dialog on network sync start
     */
    fun showLoadingDialog()

    /**
     * Created by LordDarthArt at 26.10.2019
     * Hides loading dialog after we don't need it anymore
     */
    fun hideLoadingDialog()

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

    /**
     * Created by LordDarthArt at 26.10.2019
     * Sometimes software keyboard not showing automatically, and we must close it programmatically.
     * This fun's target is to do such a thing.
     */
    fun showSoftKeyboard()

    /**
     * Created by LordDarthArt at 26.10.2019
     * Sometimes software keyboard not hiding automatically, and we must close it programmatically.
     * This fun's target is to do such a thing.
     */
    fun hideSoftKeyboard()

    fun showNetworkError()
}