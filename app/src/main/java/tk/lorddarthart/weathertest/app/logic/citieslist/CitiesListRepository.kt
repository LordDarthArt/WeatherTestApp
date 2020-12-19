package tk.lorddarthart.weathertest.app.logic.citieslist

import io.reactivex.Observable
import io.reactivex.Single
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity

interface CitiesListRepository {
    fun updateData(): Observable<Array<*>>
    fun getDataFromLocalStorage(): Single<List<ForecastEntity>>
}