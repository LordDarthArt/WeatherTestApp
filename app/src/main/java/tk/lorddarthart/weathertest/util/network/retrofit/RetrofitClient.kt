package tk.lorddarthart.weathertest.util.network.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.weathertest.util.constant.Network.BASE_URL

object RetrofitClient {
    private var ourInstance: Retrofit? = null
    const val TAG = "RetrofitClient"

    fun getInstance(): Retrofit {
        if (ourInstance == null) {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            ourInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            Log.d(TAG, "OurInstance was initialized")
        }
        return ourInstance!!
    }
}