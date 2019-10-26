package tk.lorddarthart.weathertest.app.model.forecast.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.WEATHER_DESCRIPTION_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.WEATHER_ICON_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.WEATHER_ID_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.WEATHER_MAIN_FIELD

class Weather {
    @SerializedName(WEATHER_ID_FIELD)
    @Expose
    var id = 0
    @SerializedName(WEATHER_MAIN_FIELD)
    @Expose
    lateinit var main: String
    @SerializedName(WEATHER_DESCRIPTION_FIELD)
    @Expose
    lateinit var description: String
    @SerializedName(WEATHER_ICON_FIELD)
    @Expose
    lateinit var icon: String
}