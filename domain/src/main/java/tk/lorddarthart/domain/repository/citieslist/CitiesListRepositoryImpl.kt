package tk.lorddarthart.domain.repository.citieslist

import android.content.Context
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.data.local.cities.entity.CityEntity
import tk.lorddarthart.data.local.forecast.entity.ForecastEntity
import tk.lorddarthart.data.local.forecast.item.CurrentForecastItem
import tk.lorddarthart.domain.AppDatabase
import tk.lorddarthart.domain.R
import tk.lorddarthart.domain.api.forecast.ForecastApi
import tk.lorddarthart.utils.helper.logDebug
import java.util.*

class CitiesListRepositoryImpl(private val forecastApi: ForecastApi, private val context: Context) : CitiesListRepository {

    var cities: List<CityEntity>? = null
    var forecasts: List<ForecastEntity>? = null

    override fun updateData(): Observable<Array<*>> = AppDatabase.getInstance(context).cityDao().getListOfCities()
        .subscribeOn(Schedulers.io())
        .flatMap { logDebug("getCities()"); getCities(it) }
        .flatMap { logDebug("updateDataFromNetwork()"); updateDataFromNetwork(it) }
        .doOnComplete { logDebug("doOnComplete()") }

    private fun getCities(it: List<CityEntity>): Observable<List<CityEntity>> {
        return if (it.isNullOrEmpty()) {
            AppDatabase.getInstance(context).cityDao().apply {
                addCity(CityEntity(id = UUID.randomUUID().leastSignificantBits, cityName = "Москва", lastSync = System.currentTimeMillis()))
                addCity(CityEntity(id = UUID.randomUUID().leastSignificantBits, cityName = "Санкт-Петербург", lastSync = System.currentTimeMillis()))
            }
            AppDatabase.getInstance(context).cityDao().getListOfCities()
                .flatMap {
                    cities = it
                    Observable.just(cities!!)
                }
        } else {
            cities = it
            Observable.just(cities!!)
        }
    }

    private fun updateDataFromNetwork(cities: List<CityEntity>): Observable<Array<*>> {
        val citiesObservables = mutableListOf<Observable<Unit>>()
        cities.forEach { cityEntity ->
            citiesObservables.add(
                forecastApi.getCityByName(cityEntity.cityName, context.getString(R.string.openweathermap_api_key)).map { forecast -> insertForecast(forecast) }.map { logDebug("$it") }
            )
        }
        return Observable.combineLatest(citiesObservables) { data -> data }
//            .onErrorResumeNext { error: Throwable ->
//                Observable.create { emitter -> emitter.onNext() }
//            }
            .distinctUntilChanged()
    }

    private fun insertForecast(currentForecast: CurrentForecastItem) = AppDatabase.getInstance(context).forecastDao().insertForecast(
        with(currentForecast) {
            ForecastEntity(id = currentForecast.id, weatherLat = coord.lat, weatherLon = coord.lon, weatherNow = main.temp, weatherDate = dt, weatherCity = name, weatherHigh = main.temp_max, weatherLow = main.temp_min, weatherDescription = weather[0].description, weatherHumidity = main.humidity, weatherPressure = main.pressure, weatherIcon = weather[0].icon, weatherWindSpeed = wind.speed, weatherClouds = clouds.all, weatherTimeZone = timezone, weatherSunrise = sys.sunrise, weatherSunset = sys.sunset)
        }
    )

    override fun getDataFromLocalStorage() = AppDatabase.getInstance(context).forecastDao().getListOfForecasts()
}