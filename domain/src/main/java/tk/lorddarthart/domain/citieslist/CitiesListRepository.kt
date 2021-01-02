package tk.lorddarthart.domain.citieslist

import io.reactivex.Observable
import io.reactivex.Single
import tk.lorddarthart.data.entities.ForecastEntity

interface CitiesListRepository {
    fun updateData(): Observable<Array<*>>
    fun getDataFromLocalStorage(): Single<List<ForecastEntity>>
}