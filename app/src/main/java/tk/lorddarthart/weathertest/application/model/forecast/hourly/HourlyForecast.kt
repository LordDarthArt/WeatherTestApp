package tk.lorddarthart.weathertest.application.model.forecast.hourly

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.application.model.City
import tk.lorddarthart.weathertest.util.constant.Network.CITY_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.CNT_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.COD_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.LIST_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_MESSAGE_FIELD

class HourlyForecast {
    @SerializedName(COD_FIELD)
    @Expose
    lateinit var cod: String
    @SerializedName(SYS_MESSAGE_FIELD)
    @Expose
    var message = 0.0
    @SerializedName(CNT_FIELD)
    @Expose
    var cnt = 0
    @SerializedName(LIST_FIELD)
    @Expose
    lateinit var list: List<HourlyItem>
    @SerializedName(CITY_FIELD)
    @Expose
    lateinit var city: City
}