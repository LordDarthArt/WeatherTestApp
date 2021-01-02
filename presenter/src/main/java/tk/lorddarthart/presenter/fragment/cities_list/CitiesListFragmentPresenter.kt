package tk.lorddarthart.presenter.fragment.cities_list

import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import tk.lorddarthart.data.entities.CityEntity
import tk.lorddarthart.domain.citieslist.CitiesListRepository
import tk.lorddarthart.utils.helper.logDebug
import tk.lorddarthart.utils.helper.logError
import java.util.concurrent.TimeUnit

/** Created by LordDarthArt on 26.10.2019. */
@InjectViewState
class CitiesListFragmentPresenter(private val citiesListRepository: CitiesListRepository) : MvpPresenter<CitiesListFragmentView>() {
    var cities: List<CityEntity>? = null

    fun updateData() {
        Observable.zip(
            citiesListRepository.updateData(),
            Observable.timer(2, TimeUnit.SECONDS),
            { data, _ -> data }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .observeOn(Schedulers.io())
            .flatMap {
                logDebug("getDataFromLocalStorage()"); citiesListRepository.getDataFromLocalStorage().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .map { logDebug("map"); viewState.displayData(it) }
                .onErrorReturn { viewState.showNetworkError(); logError("catched error", it) }
                .doFinally { viewState.hideLoading() }
            }
            .onErrorReturn { viewState.showNetworkError(); logError("catched error", it) }
            .subscribe()
    }

    fun onCardViewTap(view: View, position: Int) {
        viewState.openExtendedInfo()
    }

    fun onPostExecute() {
        // do something
    }
}