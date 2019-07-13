package tk.lorddarthart.weathertest.application.model.forecast.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.MAIN_HUMIDITY_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.MAIN_PRESSURE_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.MAIN_TEMP_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.MAIN_TEMP_MAX_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.MAIN_TEMP_MIN_FIELD

class Main {
    @SerializedName(MAIN_TEMP_FIELD)
    @Expose
    var temp = 0.0
    @SerializedName(MAIN_PRESSURE_FIELD)
    @Expose
    var pressure = 0
    @SerializedName(MAIN_HUMIDITY_FIELD)
    @Expose
    var humidity = 0
    @SerializedName(MAIN_TEMP_MIN_FIELD)
    @Expose
    var temp_min = 0.0
    @SerializedName(MAIN_TEMP_MAX_FIELD)
    @Expose
    var temp_max = 0.0
}