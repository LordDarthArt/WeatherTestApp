package tk.lorddarthart.weathertest.application.model.forecast.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.WIND_DEG_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.WIND_SPEED_FIELD

class Wind {
    @SerializedName(WIND_SPEED_FIELD)
    @Expose
    var speed = 0.0
    @SerializedName(WIND_DEG_FIELD)
    @Expose
    var deg = 0
}