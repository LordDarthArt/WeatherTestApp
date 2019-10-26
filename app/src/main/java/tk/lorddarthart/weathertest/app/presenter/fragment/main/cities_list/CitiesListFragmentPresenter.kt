package tk.lorddarthart.weathertest.app.presenter.fragment.main.cities_list

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.model.AppDatabase
import tk.lorddarthart.weathertest.app.model.entities.CityEntity
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.app.model.forecast.CurrentForecast
import tk.lorddarthart.weathertest.app.view.fragment.main.cities_list.CitiesListFragmentView
import tk.lorddarthart.weathertest.util.network.forecast.retrofit.RetrofitClient

/**
 * Created by LordDarthArt on 26.10.2019.
 */
@InjectViewState
class CitiesListFragmentPresenter : MvpPresenter<CitiesListFragmentView>() {
    private var sqLiteDatabase: SQLiteDatabase? = null
    private var weather = mutableListOf<CurrentForecast>()
    private var cursor: Cursor? = null
    private var cities: MutableList<CityEntity>? = null
    private var sharedPreferences: SharedPreferences? = null
    private var sharedPreferencesEditor: SharedPreferences.Editor? = null
    private var isOpen = false
    private var query: String? = null
    private var mainFragmentRouter = App.instance.getRouter()

    @SuppressLint("CheckResult")
    fun getCities(): MutableList<CityEntity> {
        if (cities == null) {
            cities = mutableListOf()
            AppDatabase.getInstance(App.instance).cityDao().getListOfCities().map { resultList ->
                for (result in resultList) {
                    cities!!.add(result)
                }
            }
        }
        return cities!!
    }

    fun updateData() {
        cities?.let {
            for (city in cities!!) {
                RetrofitClient.getInstance().getCityWithName(city.cityName, App.appid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { viewState.showLoadingDialog() }
                        .doFinally { viewState.hideLoadingDialog() }
                        .subscribe { currentForecast ->
                            AppDatabase.getInstance(App.instance).forecastDao()
                                    .insertForecast(
                                            with(currentForecast) {
                                                ForecastEntity(
                                                        id,
                                                        coord.lat,
                                                        coord.lon,
                                                        main.temp,
                                                        dt,
                                                        name,
                                                        main.temp_max,
                                                        main.temp_min,
                                                        weather[0].description,
                                                        main.humidity,
                                                        main.pressure,
                                                        weather[0].icon,
                                                        wind.speed,
                                                        clouds.all,
                                                        timezone,
                                                        sys.sunrise,
                                                        sys.sunset
                                                )
                                            }
                                    )
                        }
            }
        }
    }

    fun tableExists(): Boolean {
        val recordsCount = AppDatabase.getInstance(App.instance).cityDao().recordsCount()
        return recordsCount > 0
    }

    fun onCardViewTap(view: View, position: Int) {
        // do something
    }

    fun onPostExecute() {
        // do something
    }
}