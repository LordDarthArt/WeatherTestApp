package tk.lorddarthart.weathertest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.weathertest.util.constant.Network.FORECASTS_BASE_URL
import tk.lorddarthart.weathertest.util.constant.Network.GEO_BASE_URL
import tk.lorddarthart.weathertest.util.constant.Network.TRANSLATOR_BASE_URL
import tk.lorddarthart.weathertest.util.helper.preferences.AppPreferences
import tk.lorddarthart.weathertest.util.helper.preferences.AppPreferencesImpl
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi
import tk.lorddarthart.weathertest.util.network.translator.TranslatorApi
import tk.lorddarthart.weathertest.util.network.travelpayouts.TravelpayoutsApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGSON(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).callTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideAppPreferences(gson: Gson): AppPreferences = AppPreferencesImpl(gson)

    @Singleton
    @Provides
    fun provideForecastApiHandler(okHttpClient: OkHttpClient, gson: Gson): ForecastApi = Retrofit.Builder()
        .baseUrl(FORECASTS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ForecastApi::class.java)

    @Singleton
    @Provides
    fun provideTranslatorApiHandler(okHttpClient: OkHttpClient, gson: Gson): TranslatorApi = Retrofit.Builder()
        .baseUrl(TRANSLATOR_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(TranslatorApi::class.java)

    @Singleton
    @Provides
    fun provideTravelpayoutsApiHandler(okHttpClient: OkHttpClient, gson: Gson): TravelpayoutsApi = Retrofit.Builder()
        .baseUrl(GEO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(TravelpayoutsApi::class.java)
}