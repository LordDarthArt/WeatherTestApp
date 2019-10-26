package tk.lorddarthart.weathertest.util.network.forecast.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.weathertest.util.constant.Network.BASE_URL
import tk.lorddarthart.weathertest.util.helper.getCurrentTag
import tk.lorddarthart.weathertest.util.helper.logDebug
import tk.lorddarthart.weathertest.util.network.forecast.retrofit.requests.by_location_name.GetCurrentForecast

object RetrofitClient {
    private var ourInstance: GetCurrentForecast? = null

    fun getInstance(): GetCurrentForecast {
        if (ourInstance == null) {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            ourInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(GetCurrentForecast::class.java)

            logDebug(getCurrentTag(this), "OurInstance was initialized")
        }
        return ourInstance!!
    }
}