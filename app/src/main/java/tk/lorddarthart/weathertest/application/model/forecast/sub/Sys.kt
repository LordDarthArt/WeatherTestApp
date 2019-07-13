package tk.lorddarthart.weathertest.application.model.forecast.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.SYS_COUNTRY_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_ID_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_MESSAGE_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_SUNRISE_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_SUNSET_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.SYS_TYPE_FIELD

class Sys {
    @SerializedName(SYS_TYPE_FIELD)
    @Expose
    var type = 0
    @SerializedName(SYS_ID_FIELD)
    @Expose
    var id = 0
    @SerializedName(SYS_MESSAGE_FIELD)
    @Expose
    var message = 0.0
    @SerializedName(SYS_COUNTRY_FIELD)
    @Expose
    lateinit var country: String
    @SerializedName(SYS_SUNRISE_FIELD)
    @Expose
    var sunrise = 0L
    @SerializedName(SYS_SUNSET_FIELD)
    @Expose
    var sunset = 0L
}