package tk.lorddarthart.presenter.fragment.cities_list.extended

import moxy.InjectViewState
import moxy.MvpPresenter
import tk.lorddarthart.data.local.forecast.entity.ForecastEntity

/** Created by LordDarthArt on 26.10.2019 */
@InjectViewState
class ExtendedInfoFragmentPresenter : MvpPresenter<ExtendedInfoFragmentView>() {
    var cityForecast: ForecastEntity? = null
}