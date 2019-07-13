package tk.lorddarthart.weathertest.application.model.forecast.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.COORD_LAT_FIELD
import tk.lorddarthart.weathertest.util.constant.Network.COORD_LON_FIELD

class Coord {
    @SerializedName(COORD_LON_FIELD)
    @Expose
    var lon = 0.0
    @SerializedName(COORD_LAT_FIELD)
    @Expose
    var lat = 0.0
}