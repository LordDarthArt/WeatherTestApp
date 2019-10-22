package tk.lorddarthart.weathertest.application.presenter.main

import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.application.App
import tk.lorddarthart.weathertest.application.model.forecast.current.CurrentForecast
import tk.lorddarthart.weathertest.application.view.fragment.main.MainFragmentView
import tk.lorddarthart.weathertest.util.network.forecast.retrofit.RetrofitClient

@InjectViewState
class MainFragmentPresenter : MvpPresenter<MainFragmentView>() {
    private var sqLiteDatabase: SQLiteDatabase? = null
    private var weather = mutableListOf<CurrentForecast>()
    private var cursor: Cursor? = null
    private var cities: MutableList<String>? = null
    private var sharedPreferences: SharedPreferences? = null
    private var sharedPreferencesEditor: SharedPreferences.Editor? = null
    private var isOpen = false
    private var query: String? = null
    private var mainFragmentRouter = App.instance.getRouter()

    fun getCities(): MutableList<String> {
        if (cities == null) {

        }
        return cities!!
    }

    fun updateData() {
        cities?.let {
            for (city in cities!!) {
                RetrofitClient.getInstance().getCityWithName(city, App.appid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { viewState.showLoadingDialog() }
                        .doFinally { viewState.hideLoadingDialog() }
                        .subscribe { currentForecast ->
                            weather.add(currentForecast)
                        }
            }
        }
    }

    fun tableExists(db: SQLiteDatabase?, tableName: String?): Boolean {
        if (tableName == null || db == null || !db.isOpen) {
            return false
        }
        val cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", arrayOf("table", tableName))
        if (!cursor.moveToFirst()) {
            cursor.close()
            return false
        }
        val count = cursor.getInt(0)
        cursor.close()
        return count > 0
    }

    fun onCardViewTap(view: View, position: Int) {

    }

    fun onPostExecute() {

    }
}