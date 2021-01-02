package tk.lorddarthart.domain.network.travelpayouts

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.data.cities.CitySearchResponse
import tk.lorddarthart.utils.constant.Network.GET_PLACES

interface TravelpayoutsApi {
    @GET(GET_PLACES)
    fun getCities(
        @Query("types[]") types: String = "city",
        @Query("locale") locale: String = "en"
    ): Observable<List<CitySearchResponse>>
}