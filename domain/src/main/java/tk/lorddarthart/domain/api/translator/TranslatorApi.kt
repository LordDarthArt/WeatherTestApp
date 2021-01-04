package tk.lorddarthart.domain.api.translator

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.utils.constant.Network

interface TranslatorApi {
    @GET(Network.GET_TRANSLATION)
    fun translate(
        @Query(value = "text", encoded = true) text: String,
        @Query(value = "lang", encoded = true) lang: String,
        @Query(value = "key", encoded = true) key: String = "trnsl.1.1.20190605T100208Z.23cac5092dc349e5.105d609ed126f004c10e3733486a26ae606c1e66"
    ): Observable<String>
}