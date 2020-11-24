package tk.lorddarthart.weathertest.app.presenter.fragment.cities_list

import android.view.View


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import tk.lorddarthart.weathertest.app.logic.CitiesListRepository
import tk.lorddarthart.weathertest.app.model.entities.CityEntity
import tk.lorddarthart.weathertest.app.view.fragment.cities_list.CitiesListFragmentView
import tk.lorddarthart.weathertest.util.helper.logError
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi

/** Created by LordDarthArt on 26.10.2019. */
@InjectViewState
class CitiesListFragmentPresenter(private val citiesListRepository: CitiesListRepository) : MvpPresenter<CitiesListFragmentView>() {
    var cities: MutableList<CityEntity>? = null

    fun updateData() {
        citiesListRepository.updateData { viewState.displayData(it) }?.
            subscribeOn(Schedulers.io())?.
            observeOn(AndroidSchedulers.mainThread())?.
            doOnSubscribe { viewState.showLoadingDialog() }?.
            doOnError { logError("catched error", it) }?.
            doFinally { viewState.hideLoadingDialog() }?.
            subscribe()
    }

    fun onCardViewTap(view: View, position: Int) {
        // do something
    }

    fun onPostExecute() {
        // do something
    }
}