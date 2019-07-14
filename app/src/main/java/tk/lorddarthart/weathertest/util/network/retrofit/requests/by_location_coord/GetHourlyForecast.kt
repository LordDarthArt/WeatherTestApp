package tk.lorddarthart.weathertest.util.network.retrofit.requests.by_location_coord

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.weathertest.application.model.forecast.hourly.HourlyForecast
import tk.lorddarthart.weathertest.util.constant.Network.GET_LOCATION_HOURLY_WEATHER

interface GetHourlyForecast {
    @GET(GET_LOCATION_HOURLY_WEATHER)
    fun getHourlyForecast(
            @Query(value = "lat", encoded = true) lat: Int,
            @Query(value = "lon", encoded = true) lon: Int,
            @Query(value = "appid", encoded = true) appid: String
    ): Observable<HourlyForecast>
}