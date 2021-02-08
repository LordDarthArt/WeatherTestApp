package tk.lorddarthart.data.local.forecast.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by LordDarthArt on 23.10.2019.
 */
@Entity(tableName = "city_forecast")
data class ForecastEntity(
    @PrimaryKey @ColumnInfo(name = "weather_id") val id: Long,
    @ColumnInfo(name = "weather_lat") val weatherLat: String,
    @ColumnInfo(name = "weather_lon") val weatherLon: String,
    @ColumnInfo(name = "weather_now") val weatherNow: String,
    @ColumnInfo(name = "weather_date") val weatherDate: String,
    @ColumnInfo(name = "weather_city") val weatherCity: String?,
    @ColumnInfo(name = "weather_high") val weatherHigh: String,
    @ColumnInfo(name = "weather_low") val weatherLow: String,
    @ColumnInfo(name = "weather_description") val weatherDescription: String?,
    @ColumnInfo(name = "weather_humidity") val weatherHumidity: String,
    @ColumnInfo(name = "weather_pressure") val weatherPressure: String,
    @ColumnInfo(name = "weather_icon") val weatherIcon: String?,
    @ColumnInfo(name = "weather_windspeed") val weatherWindSpeed: String,
    @ColumnInfo(name = "weather_clouds") val weatherClouds: String,
    @ColumnInfo(name = "weather_timezone") val weatherTimeZone: String,
    @ColumnInfo(name = "weather_sunrise") val weatherSunrise: String,
    @ColumnInfo(name = "weather_sunset") val weatherSunset: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(weatherLat)
        parcel.writeString(weatherLon)
        parcel.writeString(weatherNow)
        parcel.writeString(weatherDate)
        parcel.writeString(weatherCity)
        parcel.writeString(weatherHigh)
        parcel.writeString(weatherLow)
        parcel.writeString(weatherDescription)
        parcel.writeString(weatherHumidity)
        parcel.writeString(weatherPressure)
        parcel.writeString(weatherIcon)
        parcel.writeString(weatherWindSpeed)
        parcel.writeString(weatherClouds)
        parcel.writeString(weatherTimeZone)
        parcel.writeString(weatherSunrise)
        parcel.writeString(weatherSunset)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastEntity> {
        override fun createFromParcel(parcel: Parcel): ForecastEntity {
            return ForecastEntity(parcel)
        }

        override fun newArray(size: Int): Array<ForecastEntity?> {
            return arrayOfNulls(size)
        }
    }
}