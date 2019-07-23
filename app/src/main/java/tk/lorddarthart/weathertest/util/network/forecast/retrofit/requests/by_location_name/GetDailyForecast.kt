package tk.lorddarthart.weathertest.util.network.forecast.retrofit.requests.by_location_name

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.weathertest.application.model.forecast.daily.DailyForecast
import tk.lorddarthart.weathertest.util.constant.Network.GET_LOCATION_DAILY_WEATHER

interface GetDailyForecast {
    @GET(GET_LOCATION_DAILY_WEATHER)
    fun getHourlyForecast(
            @Query(value = "q", encoded = true) q: String,
            @Query(value = "appid", encoded = true) appid: String
    ): Observable<DailyForecast>
}