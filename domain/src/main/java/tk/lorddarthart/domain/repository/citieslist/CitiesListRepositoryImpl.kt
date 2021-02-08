package tk.lorddarthart.domain.repository.citieslist

import android.content.Context
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.data.local.cities.entity.CityEntity
import tk.lorddarthart.data.local.forecast.entity.ForecastEntity
import tk.lorddarthart.data.local.forecast.item.CurrentForecastItem
import tk.lorddarthart.data.local.forecast.item.sub.*
import tk.lorddarthart.data.network.forecast.CurrentForecastResponse
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
                    Observable.just(cities ?: listOf())
                }
        } else {
            cities = it
            Observable.just(cities ?: listOf())
        }
    }

    private fun updateDataFromNetwork(cities: List<CityEntity>): Observable<Array<*>> {
        val citiesObservables = mutableListOf<Observable<Unit>>()
        cities.forEach { cityEntity ->
            citiesObservables.add(
                forecastApi.getCityByName(cityEntity.cityName, context.getString(R.string.openweathermap_api_key))
                    .map { response -> convertForecastResponse(response) }
                    .map { forecast -> insertForecast(forecast) }
                    .map { logDebug("$it") }
            )
        }
        return Observable.combineLatest(citiesObservables) { data -> data }
//            .onErrorResumeNext { error: Throwable ->
//                Observable.create { emitter -> emitter.onNext() }
//            }
            .distinctUntilChanged()
    }

    private fun convertForecastResponse(response: CurrentForecastResponse): CurrentForecastItem {
        return CurrentForecastItem(
            coord = CoordItem(
                lon = response.coord.lon,
                lat = response.coord.lat
            ),
            weather = response.weather.map { WeatherItem(it.id, it.main, it.description, it.icon) },
            base = response.base,
            main = MainItem(
                temp = response.main.temp,
                pressure = response.main.pressure,
                humidity = response.main.humidity,
                tempMin = response.main.temp_min,
                tempMax = response.main.temp_max
            ),
            visibility = response.visibility,
            wind = WindItem(
                speed = response.wind.speed,
                deg = response.wind.deg
            ),
            rain = RainItem(
                threeH = response.rain?.threeH ?: 0.0
            ),
            clouds = CloudsItem(
                all = response.clouds.all
            ),
            dt = response.dt,
            sys = SysItem(
                type = response.sys.type,
                id = response.sys.id,
                message = response.sys.message,
                country = response.sys.country,
                sunrise = response.sys.sunrise,
                sunset = response.sys.sunset
            ),
            timezone = response.timezone,
            id = response.id,
            name = response.name,
            cod = response.cod
        )
    }

    private fun insertForecast(currentForecast: CurrentForecastItem) = AppDatabase.getInstance(context).forecastDao().insertForecast(
        with(currentForecast) {
            ForecastEntity(id = currentForecast.id, weatherLat = coord.lat.toString(), weatherLon = coord.lon.toString(), weatherNow = main.temp.toString(), weatherDate = dt.toString(), weatherCity = name, weatherHigh = main.tempMax.toString(), weatherLow = main.tempMin.toString(), weatherDescription = weather[0].description, weatherHumidity = main.humidity.toString(), weatherPressure = main.pressure.toString(), weatherIcon = weather[0].icon, weatherWindSpeed = wind.speed.toString(), weatherClouds = clouds.all.toString(), weatherTimeZone = timezone.toString(), weatherSunrise = sys.sunrise.toString(), weatherSunset = sys.sunset.toString())
        }
    )

    override fun getDataFromLocalStorage() = AppDatabase.getInstance(context).forecastDao().getListOfForecasts()
}