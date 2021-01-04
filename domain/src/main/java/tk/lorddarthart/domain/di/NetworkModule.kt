package tk.lorddarthart.domain.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.domain.api.forecast.ForecastApi
import tk.lorddarthart.domain.api.translator.TranslatorApi
import tk.lorddarthart.domain.api.travelpayouts.TravelpayoutsApi
import tk.lorddarthart.utils.constant.Network
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).callTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideForecastApiHandler(okHttpClient: OkHttpClient, gson: Gson): ForecastApi = Retrofit.Builder()
        .baseUrl(Network.FORECASTS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ForecastApi::class.java)

    @Singleton
    @Provides
    fun provideTranslatorApiHandler(okHttpClient: OkHttpClient, gson: Gson): TranslatorApi = Retrofit.Builder()
        .baseUrl(Network.TRANSLATOR_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(TranslatorApi::class.java)

    @Singleton
    @Provides
    fun provideTravelpayoutsApiHandler(okHttpClient: OkHttpClient, gson: Gson): TravelpayoutsApi = Retrofit.Builder()
        .baseUrl(Network.GEO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(TravelpayoutsApi::class.java)
}