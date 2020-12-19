package tk.lorddarthart.weathertest.util.network.travelpayouts

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.weathertest.app.model.cities.CitySearchResponse
import tk.lorddarthart.weathertest.util.constant.Network.GET_PLACES

interface TravelpayoutsApi {
    @GET(GET_PLACES)
    fun getCities(
        @Query("types[]") types: String = "city",
        @Query("locale") locale: String = "en"
    ): Observable<List<CitySearchResponse>>
}