package tk.lorddarthart.weathertest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.weathertest.util.constant.Network.BASE_URL
import tk.lorddarthart.weathertest.util.helper.AppPreferences
import tk.lorddarthart.weathertest.util.helper.AppPreferencesImpl
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi
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
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): ForecastApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ForecastApi::class.java)
}