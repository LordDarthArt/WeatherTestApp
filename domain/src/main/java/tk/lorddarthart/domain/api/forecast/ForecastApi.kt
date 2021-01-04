package tk.lorddarthart.domain.api.forecast

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.data.local.forecast.item.CurrentForecastItem
import tk.lorddarthart.utils.constant.Network.GET_PREFERRED_LOCATION_WEATHER

interface ForecastApi {
    @GET(GET_PREFERRED_LOCATION_WEATHER)
    fun getCityByName(
            @Query(value = "q", encoded = true) q: String,
            @Query(value = "appid", encoded = true) appid: String,
            @Query(value = "units", encoded = true) units: String = "metric"
    ): Observable<CurrentForecastItem>

    @GET(GET_PREFERRED_LOCATION_WEATHER)
    fun getCityById(
        @Query(value = "id", encoded = true) id: Int,
        @Query(value = "appid", encoded = true) appid: String,
        @Query(value = "units", encoded = true) units: String = "metric"
    ): Observable<CurrentForecastItem>

    @GET(GET_PREFERRED_LOCATION_WEATHER)
    fun getCityByCoords(
        @Query(value = "lat", encoded = true) lat: Int,
        @Query(value = "lon", encoded = true) lon: Int,
        @Query(value = "appid", encoded = true) appid: String,
        @Query(value = "units", encoded = true) units: String = "metric"
    ): Observable<CurrentForecastItem>
}