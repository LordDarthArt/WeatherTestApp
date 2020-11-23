package tk.lorddarthart.weathertest.app.logic

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.model.AppDatabase
import tk.lorddarthart.weathertest.app.model.entities.CityEntity
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.app.model.forecast.CurrentForecast
import tk.lorddarthart.weathertest.util.helper.logError
import tk.lorddarthart.weathertest.util.network.forecast.ForecastApi
import java.util.*

class CitiesListRepositoryImpl(private val forecastApi: ForecastApi): CitiesListRepository {
    override fun updateData(completeCallback: (List<ForecastEntity>) -> Unit) : Observable<Unit>? {
        var cities: List<CityEntity>? = null
        return AppDatabase.getInstance(App.instance).cityDao().getListOfCities()
            .subscribeOn(Schedulers.io())
            .flatMap {
                if (it.isNullOrEmpty()) {
                    AppDatabase.getInstance(App.instance).cityDao().apply {
                        addCity(CityEntity(id = UUID.randomUUID().leastSignificantBits, cityName = "Москва", lastSync = System.currentTimeMillis()))
                        addCity(CityEntity(id = UUID.randomUUID().leastSignificantBits, cityName = "Санкт-Петербург", lastSync = System.currentTimeMillis()))
                    }
                    AppDatabase.getInstance(App.instance).cityDao().getListOfCities().map { cities = it }
                } else {
                    cities = it
                    Observable.just(cities!!)
                }
            }
            .flatMap { updateDataFromNetwork(cities!!) }
            .flatMap { forecastApi.getCityByName(it.cityName, App.appid) }
            .flatMap { insertForecast(it) }
            .flatMap { displayDataFromLocalStorage() }
            .observeOn(AndroidSchedulers.mainThread())
            .map { completeCallback(it) }
    }

    private fun updateDataFromNetwork(cities: List<CityEntity>) = Observable.fromIterable(cities)

    private fun insertForecast(currentForecast: CurrentForecast) = Observable.just(AppDatabase.getInstance(App.instance).forecastDao().insertForecast(
            with(currentForecast) {
                ForecastEntity(id = currentForecast.id, weatherLat = coord.lat, weatherLon = coord.lon, weatherNow = main.temp, weatherDate = dt, weatherCity = name, weatherHigh = main.temp_max, weatherLow = main.temp_min, weatherDescription = weather[0].description, weatherHumidity = main.humidity, weatherPressure = main.pressure, weatherIcon = weather[0].icon, weatherWindSpeed = wind.speed, weatherClouds = clouds.all, weatherTimeZone = timezone, weatherSunrise = sys.sunrise, weatherSunset = sys.sunset)
            }
        ))

    private fun displayDataFromLocalStorage() = AppDatabase.getInstance(App.instance).forecastDao().getListOfForecasts()
}