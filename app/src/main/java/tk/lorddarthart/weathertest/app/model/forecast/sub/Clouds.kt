package tk.lorddarthart.weathertest.app.model.forecast.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.CLOUDS_ALL_FIELD

class Clouds {
    @SerializedName(CLOUDS_ALL_FIELD)
    @Expose
    var all = 0
}