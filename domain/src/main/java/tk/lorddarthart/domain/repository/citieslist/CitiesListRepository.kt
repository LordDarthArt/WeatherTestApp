package tk.lorddarthart.domain.repository.citieslist

import io.reactivex.Observable
import io.reactivex.Single
import tk.lorddarthart.data.local.forecast.entity.ForecastEntity

interface CitiesListRepository {
    fun updateData(): Observable<Array<*>>
    fun getDataFromLocalStorage(): Single<List<ForecastEntity>>
}