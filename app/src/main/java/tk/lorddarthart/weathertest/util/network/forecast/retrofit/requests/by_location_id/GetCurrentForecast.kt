package tk.lorddarthart.weathertest.util.network.forecast.retrofit.requests.by_location_id

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.weathertest.app.model.forecast.CurrentForecast
import tk.lorddarthart.weathertest.util.constant.Network.GET_PREFERRED_LOCATION_WEATHER

interface GetCurrentForecast {
    @GET(GET_PREFERRED_LOCATION_WEATHER)
    fun getCityWithName(
            @Query(value = "id", encoded = true) id: Int,
            @Query(value = "appid", encoded = true) appid: String
    ): Observable<CurrentForecast>
}