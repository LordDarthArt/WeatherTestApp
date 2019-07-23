package tk.lorddarthart.weathertest.util.network.forecast.retrofit.requests.by_location_coord

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.weathertest.application.model.forecast.current.CurrentForecast
import tk.lorddarthart.weathertest.util.constant.Network.GET_PREFERRED_LOCATION_WEATHER

interface GetCurrentForecast {
    @GET(GET_PREFERRED_LOCATION_WEATHER)
    fun getCityWithName(
            @Query(value = "lat", encoded = true) lat: Int,
            @Query(value = "lon", encoded = true) lon: Int,
            @Query(value = "appid", encoded = true) appid: String
    ): Observable<CurrentForecast>
}