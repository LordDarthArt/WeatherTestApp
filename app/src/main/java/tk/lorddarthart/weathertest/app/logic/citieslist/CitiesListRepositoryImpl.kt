package tk.lorddarthart.weathertest.app.logic.citieslist

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.weathertest.app.WeatherTestApp
import tk.lorddarthart.weathertest.app.model.AppDatabase
import tk.lorddarthart.weathertest.app.model.entities.CityEntity
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.app.model.forecast.CurrentForecast
import tk.lorddarthart.weathertest.util.helper.logDebug
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi
import java.util.*

class CitiesListRepositoryImpl(private val forecastApi: ForecastApi): CitiesListRepository {
    var cities: List<CityEntity>? = null
    var forecasts: List<ForecastEntity>? = null

    override fun updateData(): Observable<Array<*>> = AppDatabase.getInstance(WeatherTestApp.instance).cityDao().getListOfCities()
        .subscribeOn(Schedulers.io())
        .flatMap { logDebug("getCities()"); getCities(it) }
        .flatMap { logDebug("updateDataFromNetwork()"); updateDataFromNetwork(it) }
        .doOnComplete { logDebug("doOnComplete()") }

    private fun getCities(it: List<CityEntity>): Observable<List<CityEntity>> {
        return if (it.isNullOrEmpty()) {
            AppDatabase.getInstance(WeatherTestApp.instance).cityDao().apply {
                addCity(CityEntity(id = UUID.randomUUID().leastSignificantBits, cityName = "Москва", lastSync = System.currentTimeMillis()))
                addCity(CityEntity(id = UUID.randomUUID().leastSignificantBits, cityName = "Санкт-Петербург", lastSync = System.currentTimeMillis()))
            }
            AppDatabase.getInstance(WeatherTestApp.instance).cityDao().getListOfCities()
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
        cities.forEach {
            citiesObservables.add(
                forecastApi.getCityByName(it.cityName, WeatherTestApp.appid).map { forecast -> insertForecast(forecast) }.map { logDebug("$it") }
            )
        }
        return Observable.combineLatest(citiesObservables) { data -> data }
//            .onErrorResumeNext { error: Throwable ->
//                Observable.create { emitter -> emitter.onNext() }
//            }
            .distinctUntilChanged()
    }

    private fun insertForecast(currentForecast: CurrentForecast) = AppDatabase.getInstance(WeatherTestApp.instance).forecastDao().insertForecast(
            with(currentForecast) {
                ForecastEntity(id = currentForecast.id, weatherLat = coord.lat, weatherLon = coord.lon, weatherNow = main.temp, weatherDate = dt, weatherCity = name, weatherHigh = main.temp_max, weatherLow = main.temp_min, weatherDescription = weather[0].description, weatherHumidity = main.humidity, weatherPressure = main.pressure, weatherIcon = weather[0].icon, weatherWindSpeed = wind.speed, weatherClouds = clouds.all, weatherTimeZone = timezone, weatherSunrise = sys.sunrise, weatherSunset = sys.sunset)
            }
        )

    override fun getDataFromLocalStorage() = AppDatabase.getInstance(WeatherTestApp.instance).forecastDao().getListOfForecasts()
}