package tk.lorddarthart.weathertest.app.logic

import io.reactivex.Observable
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity

interface CitiesListRepository {
    fun updateData(completeCallback: (List<ForecastEntity>) -> Unit): Observable<Unit>?
}