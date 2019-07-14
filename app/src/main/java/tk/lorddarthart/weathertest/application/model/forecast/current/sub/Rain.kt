package tk.lorddarthart.weathertest.application.model.forecast.current.sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.weathertest.util.constant.Network.RAIN_3H_FIELD

class Rain {
    @SerializedName(RAIN_3H_FIELD)
    @Expose
    var threeH = 0.0
}