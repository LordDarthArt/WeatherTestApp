package tk.lorddarthart.weathertest.util.network.retrofit.requests

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.weathertest.application.model.forecast.Forecast
import tk.lorddarthart.weathertest.util.constant.Network.GET_PREFERRED_LOCATION_WEATHER

interface GetForecast {
    @GET(GET_PREFERRED_LOCATION_WEATHER)
    fun getCityWithName(
            @Query(value = "q", encoded = true) q: String,
            @Query(value = "appid", encoded = true) appid: String
    ): Observable<Forecast>
}